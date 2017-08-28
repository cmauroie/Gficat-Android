package com.cmauroie.gficatandroid.requestmanager;

import com.cmauroie.gficatandroid.modeldata.RequestLogin;
import com.cmauroie.gficatandroid.modeldata.ResponseAlbumData;
import com.cmauroie.gficatandroid.modeldata.ResponseLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mauricio on 8/26/17.
 */

public interface ApiInterface {

    /*@POST("oauth/token")
    Call<> loginOAuth()

    //@POST("preLiquidarServicio")*/
    @POST("oauth/token")
    Call<ResponseLogin> getLoginOauth(@Body RequestLogin requestLogin);


    @GET("me/album-folders")
    Call<List<ResponseAlbumData>> getAlbumFolders();
/*
    @GET("movie/popular")
    Call<ModelDataResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<ModelDataResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<ModelDataResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<ModelDataResponse> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query);
*/
    /*@GET("movie/{id}")
    Call<ModelDataResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/
}
