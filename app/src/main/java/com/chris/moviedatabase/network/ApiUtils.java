package com.chris.moviedatabase.network;

import com.chris.moviedatabase.utils.Constants;

public class ApiUtils {

    private ApiUtils() {}

    public static APIService getAPIService() {

        return RetrofitClient.getClient(Constants.BASE_URL).create(APIService.class);
    }
}