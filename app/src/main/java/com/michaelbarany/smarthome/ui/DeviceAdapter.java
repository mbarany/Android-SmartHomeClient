package com.michaelbarany.smarthome.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.michaelbarany.smarthome.api.models.AbstractDeviceModel;
import com.michaelbarany.smarthome.ui.widgets.devices.AbstractDevice;
import com.michaelbarany.smarthome.ui.widgets.devices.DeviceFactory;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends ArrayAdapter<AbstractDeviceModel> {
    public DeviceAdapter(Context context) {
        super(context, -1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        AbstractDevice widget = DeviceFactory.create(getItem(position));
        View view = inflater.inflate(widget.getLayoutResourceId(), parent, false);
        widget.attach(view);

        return view;
    }

    public List<AbstractDeviceModel> getList() {
        List<AbstractDeviceModel> list = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            list.add(getItem(i));
        }
        return list;
    }
}
