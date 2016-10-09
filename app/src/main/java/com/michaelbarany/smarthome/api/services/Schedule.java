package com.michaelbarany.smarthome.api.services;


import com.michaelbarany.smarthome.api.models.ScheduleResultModel;

import retrofit.Callback;
import retrofit.http.GET;

public interface Schedule {
    @GET("/schedule")
    void list(Callback<ScheduleResultModel> response);
}
