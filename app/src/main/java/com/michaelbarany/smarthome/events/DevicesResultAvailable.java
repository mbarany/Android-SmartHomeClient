package com.michaelbarany.smarthome.events;

import com.michaelbarany.smarthome.api.models.DevicesResultModel;

public class DevicesResultAvailable {
    private final DevicesResultModel mDevicesResultModel;

    public DevicesResultAvailable(DevicesResultModel devicesResultModel) {
        mDevicesResultModel = devicesResultModel;
    }

    public DevicesResultModel get() {
        return mDevicesResultModel;
    }
}
