package com.michaelbarany.smarthome.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.models.ScheduleResultModel;

public class ScheduleAdapter extends ArrayAdapter<ScheduleResultModel.EventModel> {
    public ScheduleAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ScheduleResultModel.EventModel model = getItem(position);
        View view = inflater.inflate(R.layout.activity_schedule_event_item, parent, false);

        ((TextView) view.findViewById(R.id.line1)).setText(model.date);
        ((TextView) view.findViewById(R.id.line2)).setText("Devices: " + model.devices);
        ((TextView) view.findViewById(R.id.line3)).setText("Scenes: " + model.scenes);

        return view;
    }
}
