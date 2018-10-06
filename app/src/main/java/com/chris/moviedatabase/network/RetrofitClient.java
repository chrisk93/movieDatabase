package com.chris.moviedatabase.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //cuando se vaya a utilizar rxjava
                    .addConverterFactory(ScalarsConverterFactory.create()) //convertidor a String
                    .addConverterFactory(GsonConverterFactory.create()) //convertidor pojo Gson
                    .build();
        }
        return retrofit;
    }

}
