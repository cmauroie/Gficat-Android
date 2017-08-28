package com.cmauroie.gficatandroid.requestmanager;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cmauroie.gficatandroid.Constants.BASE_URL;

/**
 * Created by Mauricio on 8/26/17.
 */

public class ApiClient {

    //public static final String BASE_URL = "https://api.gfycat.com/v1/";
    //public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w300/";
    //https://api.gfycat.com/v1/me/album-folders
    private static Retrofit retrofit = null;
    private static final String TAG = ApiClient.class.getName();

    private static OkHttpClient getRequestHeader(final String authorization) {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .authenticator(new Authenticator(){
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        //String credential = Credentials.basic("name", "password");
                        String credential = Credentials.basic("name", "password");
                        return response.request().newBuilder().header("Authorization", "Bearer " + authorization).build();
                        //return response.request().newBuilder().header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDM4NzQ3NzYsImlzcyI6IjJfNVp5Y1YwIiwicm9sZXMiOlsiVXNlciJdLCJzY29wZXMiOiIiLCJzdWIiOiJ1c2VyL2NtYXVyb2llIn0.3iWKRWFxdlrAl3IYH8fkG7LJ0EDx-59VrpUbcs03yrs").build();

                    }
                })
                .build();
        return okHttpClient;
    }

    public static Retrofit getClient(String authotization) {
        Log.i(TAG, "token: " + authotization);
    //if (retrofit == null) {
            retrofit = null;
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getRequestHeader(authotization))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        //}
        return retrofit;
    }
}