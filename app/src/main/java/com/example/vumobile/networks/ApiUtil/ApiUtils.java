package com.example.vumobile.networks.ApiUtil;

import android.util.Log;

import com.example.vumobile.networks.Remote.APIService;
import com.example.vumobile.networks.Remote.RetroClient;

public class ApiUtils {
    public static APIService getApiService(String baseURL){
        Log.d("responsedata", baseURL);
        return RetroClient.getClient(baseURL).create(APIService.class);
    }
}
