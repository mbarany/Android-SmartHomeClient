package com.michaelbarany.smarthome.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.Api;
import com.michaelbarany.smarthome.api.models.ScheduleResultModel;
import com.michaelbarany.smarthome.api.services.Schedule;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ScheduleActivity extends Activity {
    @InjectView(R.id.listview)
    ListView mListView;

    ScheduleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ButterKnife.inject(this);

        mAdapter = new ScheduleAdapter(this);
        mListView.setAdapter(mAdapter);

        Schedule service = Api.getRestAdapter().create(Schedule.class);
        service.list(new Callback<ScheduleResultModel>() {
            @Override
            public void success(ScheduleResultModel scheduleResultModel, Response response) {
                mAdapter.clear();
                mAdapter.addAll(scheduleResultModel.events);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }
}
