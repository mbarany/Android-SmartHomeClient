package com.michaelbarany.smarthome.api.models;

import java.util.Map;

public class ThermostatModel extends AbstractDeviceModel {
    Map<String, String> status;

    public Map<String, String> getStatus() {
        return status;
    }

    public Revertable setStatus(final String key, String value) {
        final String oldValue = getStatus().get(key);
        getStatus().put(key, value);
        return new Revertable() {
            @Override
            public void revert() {
                setStatus(key, oldValue);
            }
        };
    }

    public int getTemperature() {
        return Integer.parseInt(getStatus().get("temperature"));
    }

    @Override
    public String getStatusText() {
        return getStatus().get("mode") + " " + getStatus().get("temperature") + "°F";
    }
}
