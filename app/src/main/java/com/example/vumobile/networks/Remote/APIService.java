package com.example.vumobile.networks.Remote;


import com.example.vumobile.model.ImageModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/users?page=1")
    Call<ImageModel> getImageData();
}
