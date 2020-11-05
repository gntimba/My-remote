package com.crefstech.myremote.API;

import com.crefstech.myremote.models.Auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIIinterface {

    @POST("api/auth")
    Call<Auth> login(@Body Auth auth);
}
