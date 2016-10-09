package com.michaelbarany.smarthome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.michaelbarany.smarthome.App;
import com.michaelbarany.smarthome.BaseActivity;
import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.models.DevicesResultModel;
import com.michaelbarany.smarthome.events.DevicesResultAvailable;
import com.michaelbarany.smarthome.events.RefreshDeviceList;
import com.michaelbarany.smarthome.events.ToastEvent;
import com.michaelbarany.smarthome.services.Callback;
import com.michaelbarany.smarthome.services.DeviceData;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {
    private DeviceAdapter mAdapter;

    @InjectView(R.id.listview)
    ListView mListView;
    @InjectView(R.id.button)
    Button mButton;

    @Inject
    DeviceData mDeviceData;

    private Callback<DevicesResultModel> mCallback = new Callback<DevicesResultModel>() {
        @Override
        public void onResult(DevicesResultModel result) {
            App.getBus().post(new DevicesResultAvailable(result));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getBus().register(this);
        ButterKnife.inject(this);

        mAdapter = new DeviceAdapter(this);
        mListView.setAdapter(mAdapter);

        boolean shouldRefreshCache = null == savedInstanceState;
        mDeviceData.getCache(shouldRefreshCache, mCallback);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getBus().unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDeviceData.saveState();
    }

    @Subscribe
    public void refreshDeviceList(RefreshDeviceList event) {
        Log.d("_", "refreshDeviceList!");
        //mDeviceData.getLive(mCallback);
        mAdapter.notifyDataSetChanged();

    }

    @Subscribe
    public void devicesResultAvailable(DevicesResultAvailable event) {
        DevicesResultModel result = event.get();

        mAdapter.clear();
        if (null != result.switches) {
            mAdapter.addAll(result.switches);
        }
        if (null != result.dimmableSwitches) {
            mAdapter.addAll(result.dimmableSwitches);
        }
        if (null != result.thermostats) {
            mAdapter.addAll(result.thermostats);
        }
    }

    @Subscribe
    public void toastEvent(ToastEvent event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
