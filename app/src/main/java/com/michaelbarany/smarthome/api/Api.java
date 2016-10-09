package com.michaelbarany.smarthome.api;

import com.michaelbarany.smarthome.BuildConfig;
import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Api {
    private static RestAdapter sRestAdapter;
    private static final String PATH_PREFIX = "/api";

    static {
        OkHttpClient client = new OkHttpClient();
        client.setAuthenticator(new ApiAuthenticator());

        if (!BuildConfig.API_SECURE_DOMAIN.isEmpty() && !BuildConfig.API_SECURE_PIN.isEmpty()) {
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(BuildConfig.API_SECURE_DOMAIN, BuildConfig.API_SECURE_PIN)
                .build();
            client.setCertificatePinner(certificatePinner);
        }

        sRestAdapter = new RestAdapter.Builder()
            .setEndpoint(BuildConfig.API_HOST + PATH_PREFIX)
            .setClient(new OkClient(client))
            .setLogLevel(RestAdapter.LogLevel.BASIC)
            .build();
    }

    public static RestAdapter getRestAdapter() {
        return sRestAdapter;
    }
}
