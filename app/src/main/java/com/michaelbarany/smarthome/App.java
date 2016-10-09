package com.michaelbarany.smarthome;

import android.app.Application;

import com.michaelbarany.smarthome.services.modules.AndroidModule;
import com.michaelbarany.smarthome.services.modules.MainActivityModule;
import com.squareup.otto.Bus;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class App extends Application {
    private ObjectGraph graph;
    private static final Bus sBus = new Bus();

    @Override public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
            new AndroidModule(this),
            new MainActivityModule()
        );
    }

    public void inject(Object object) {
        graph.inject(object);
    }

    public static Bus getBus() {
        return sBus;
    }
}
