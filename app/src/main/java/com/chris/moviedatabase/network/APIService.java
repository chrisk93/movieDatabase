package com.chris.moviedatabase.network;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {

    //@FormUrlEncoded
    @GET("search/keyword")
    @Headers({"Content-Type: application/json"})
    Observable<String> searchKeyWord(@Query("api_key") String api_key,
                                     @Query("query") String query);

    //@GET("movie/{movie_id}")
    //@GET("movie/{movie_id}?api_key={api_key}")
    @GET("movie/195884")
    @Headers({"Content-Type: application/json"})
    //Observable<String> getDetailsMovie(@Path("movie_id") String movie_id, @Query("api_key") String api_key);
    Observable<String> getDetailsMovie(@Query("api_key") String api_key);



}