package com.cmauroie.gficatandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cmauroie.gficatandroid.adapter.DataAdapter;
import com.cmauroie.gficatandroid.modeldata.ModelDataAlbum;
import com.cmauroie.gficatandroid.modeldata.ResponseAlbumData;
import com.cmauroie.gficatandroid.requestmanager.ApiClient;
import com.cmauroie.gficatandroid.requestmanager.ApiInterface;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private static SharedPreferences preferences;
    private Context context;
    private List<ModelDataAlbum> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tokenAcces = preferences.getString(Constants.PREFERENCE_TOKEN,null);

        taskRequestOauth(tokenAcces);
    }

    private void taskRequestOauth(String oauth){

        ApiInterface apiService =
                ApiClient.getClient(oauth).create(ApiInterface.class);
        Call<List<ResponseAlbumData>> call = apiService.getAlbumFolders();
        call.enqueue(new Callback<List<ResponseAlbumData>>() {
            @Override
            public void onResponse(Call<List<ResponseAlbumData>> call, Response<List<ResponseAlbumData>> response) {
                int statusCode = response.code();
                Log.i(TAG, "Response: " + response.code());
                Gson gson = new Gson();
                String data = gson.toJson(response.body().get(0).getNodes());
                Log.i(TAG, "Response info: " + data);
                albumList = response.body().get(0).getNodes();

                dataAlbum(albumList);
            }

            @Override
            public void onFailure(Call<List<ResponseAlbumData>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure Album: " + t.getMessage());
            }
        });
    }

    private void dataAlbum(List<ModelDataAlbum> albumList){

        if(MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            // Portrait Mode
            recycler = (RecyclerView) findViewById(R.id.id_recycler);
            recycler.setHasFixedSize(true);
            lManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(lManager);
            adapter = new DataAdapter(albumList, MainActivity.this);
            recycler.setAdapter(adapter);
        } else {
            // Landscape Mode
            recycler = (RecyclerView) findViewById(R.id.id_recycler);
            recycler.setHasFixedSize(true);
            lManager = new GridLayoutManager(MainActivity.this, 2 , GridLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(lManager);
            adapter = new DataAdapter(albumList, MainActivity.this);
            recycler.setAdapter(adapter);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            dataAlbum(albumList);
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            dataAlbum(albumList);
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
