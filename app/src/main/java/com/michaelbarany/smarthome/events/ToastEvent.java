package com.michaelbarany.smarthome.events;

public class ToastEvent {
    private final String mMessage;

    public ToastEvent(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
