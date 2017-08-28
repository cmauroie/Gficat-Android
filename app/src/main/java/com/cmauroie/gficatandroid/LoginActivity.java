package com.cmauroie.gficatandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cmauroie.gficatandroid.modeldata.RequestLogin;
import com.cmauroie.gficatandroid.modeldata.ResponseLogin;
import com.cmauroie.gficatandroid.requestmanager.ApiClient;
import com.cmauroie.gficatandroid.requestmanager.ApiInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getName();
    private static final int REQUEST_SIGNUP = 0;

    private EditText mUserText;
    private EditText mPassText;
    private Button mLoginButtom;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserText = (EditText)findViewById(R.id.input_username);
        mPassText = (EditText)findViewById(R.id.input_password);
        mLoginButtom = (Button)findViewById(R.id.btn_login);
        mLoginButtom.setOnClickListener(this);

        context = getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login (){
        if(!validate()){
            onLoginFailed();
            return;
        }
        mLoginButtom.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Iniciando sesión. . .");
        progressDialog.show();
        String user = mUserText.getText().toString();
        String password = mPassText.getText().toString();
        taskRequestOauth(user,password);

    }

    private void taskRequestOauth(String user, String password){

        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setClient_id(Constants.CLIENT_ID);
        requestLogin.setClient_secret(Constants.CLIENT_SECRET);
        requestLogin.setGrant_type(Constants.GRANT_TYPE);
        requestLogin.setUsername(user);
        requestLogin.setPassword(password);

        ApiInterface apiService =
                ApiClient.getClient("").create(ApiInterface.class);
        Call<ResponseLogin> call = apiService.getLoginOauth(requestLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                int statusCode = response.code();
                Log.i(TAG, "Response: " + response.toString());

                Gson gson = new Gson();
                String data = gson.toJson(response);
                Log.i(TAG, "Response: " + data);
                editor.putString(Constants.PREFERENCE_TOKEN, response.body().getAccess_token());
                editor.apply();

                onLoginSuccess();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure login: " + t.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_SIGNUP){
            if(resultCode == RESULT_OK){
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private boolean validate(){
        boolean valid = true;
        String user = mUserText.getText().toString();
        String password = mPassText.getText().toString();

        if(user.isEmpty()){
            mUserText.setError("Ingrese un usuario valido");
            valid = false;
        }else{
            mUserText.setError(null);
        }

        if(password.isEmpty()){
            mPassText.setError("Ingrese una contraseña valida");
        }else{
            mPassText.setError(null);
        }
        return valid;
    }

    private void onLoginFailed(){
        mLoginButtom.setEnabled(true);
    }

    private void onLoginSuccess(){
        mLoginButtom.setEnabled(true);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
