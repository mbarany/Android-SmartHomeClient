package com.michaelbarany.smarthome.api.services;


import com.michaelbarany.smarthome.api.models.DevicesResultModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Devices {
    @GET("/devices")
    void list(Callback<DevicesResultModel> response);

    @POST("/devices/{deviceId}/{state}")
    void setState(@Path("deviceId") String deviceId, @Path("state") String state, Callback<Response> response);
}
