package com.michaelbarany.smarthome.api.models;

public class SwitchModel extends AbstractDeviceModel {
    int status;

    public int getStatus() {
        return status;
    }

    public Revertable setStatus(int aStatus) {
        final int oldValue = status;
        status = aStatus;
        return new Revertable() {
            @Override
            public void revert() {
                setStatus(oldValue);
            }
        };
    }

    @Override
    public String getStatusText() {
        return status == 0 ? "Off" : "On";
    }
}
