package com.michaelbarany.smarthome.api.models;

public class AbstractDeviceModel {
    String id;
    String type;
    String name;
    String statusText;

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatusText() {
        return statusText;
    }
}
