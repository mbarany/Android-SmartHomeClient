package com.michaelbarany.smarthome.services;

public interface Callback<T> {
    void onResult(T result);
}
