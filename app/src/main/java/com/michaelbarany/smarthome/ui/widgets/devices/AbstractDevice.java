package com.michaelbarany.smarthome.ui.widgets.devices;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;

import com.michaelbarany.smarthome.App;
import com.michaelbarany.smarthome.api.Api;
import com.michaelbarany.smarthome.api.models.ErrorModel;
import com.michaelbarany.smarthome.api.models.Revertable;
import com.michaelbarany.smarthome.api.services.Devices;
import com.michaelbarany.smarthome.events.RefreshDeviceList;
import com.michaelbarany.smarthome.events.ToastEvent;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class AbstractDevice {
    public abstract void attach(View view);

    @LayoutRes
    public abstract int getLayoutResourceId();

    protected void setState(String deviceId, String state, final Revertable revertable) {
        App.getBus().post(new RefreshDeviceList());
        Devices service = Api.getRestAdapter().create(Devices.class);
        Log.d("_", deviceId + "/" + state);
        service.setState(deviceId, state, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
            }

            @Override
            public void failure(RetrofitError error) {
                if (null != revertable) {
                    revertable.revert();
                    App.getBus().post(new RefreshDeviceList());
                }

                if (error.getKind() == RetrofitError.Kind.HTTP) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    App.getBus().post(new ToastEvent(errorModel.toString()));
                }
            }
        });
    }
}
