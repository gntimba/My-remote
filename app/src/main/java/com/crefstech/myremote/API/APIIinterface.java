package com.crefstech.myremote.API;

import com.crefstech.myremote.models.Auth;
import com.crefstech.myremote.models.Device;
import com.crefstech.myremote.models.MainDevice;
import com.crefstech.myremote.models.UserDevice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIIinterface {

    @POST("api/auth")
    Call<Auth> login(@Body Auth auth);

    @POST("api/addUserDevices")
    Call<UserDevice> addUserDevices(@Header("Authorization") String Authorization, @Body UserDevice userDevice);

    @GET("api/getDevices")
    Call<MainDevice> getDevices(@Header("Authorization") String Authorization);

    @GET("api/getUserDevices")
    Call<List<Device>> getUserDevices(@Header("Authorization") String Authorization);
}
