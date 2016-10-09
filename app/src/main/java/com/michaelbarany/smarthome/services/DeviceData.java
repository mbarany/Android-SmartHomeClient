package com.michaelbarany.smarthome.services;

import com.michaelbarany.smarthome.api.Api;
import com.michaelbarany.smarthome.api.models.DevicesResultModel;
import com.michaelbarany.smarthome.api.services.Devices;

import javax.inject.Inject;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class DeviceData {
    private static final String CACHE_FILE = DevicesResultModel.class.toString();

    private DevicesResultModel mDevicesResultModel;

    @Inject
    JsonCache mJsonCache;

    public void getCache(boolean shouldRefreshCache, final Callback<DevicesResultModel> callback) {
        DevicesResultModel result = mJsonCache.read(CACHE_FILE, DevicesResultModel.class);
        if (null != result) {
            if (null == mDevicesResultModel) {
                mDevicesResultModel = result;
            }
            callback.onResult(result);
            if (!shouldRefreshCache) {
                return;
            }
        }
        getLive(callback);
    }

    public void getLive(final Callback<DevicesResultModel> callback) {
        Devices service = Api.getRestAdapter().create(Devices.class);
        service.list(new retrofit.Callback<DevicesResultModel>() {
            @Override
            public void success(DevicesResultModel result, Response response) {
                mJsonCache.write(CACHE_FILE, result);
                mDevicesResultModel = result;
                callback.onResult(result);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    public void saveState() {
        if (null == mDevicesResultModel) {
            return;
        }
        mJsonCache.write(CACHE_FILE, mDevicesResultModel);
    }
}
