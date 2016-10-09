package com.michaelbarany.smarthome.api;

import com.michaelbarany.smarthome.BuildConfig;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

public class ApiAuthenticator implements Authenticator {
    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
        String credential = Credentials.basic(BuildConfig.API_KEY, "");
        return response.request().newBuilder().header("Authorization", credential).build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        return null;
    }
}
