package com.michaelbarany.smarthome.ui.widgets.devices;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.models.ThermostatModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Thermostat extends AbstractDevice {
    private final ThermostatModel mModel;

    @InjectView(R.id.title)
    TextView mTitle;

    @InjectView(R.id.description)
    TextView mDescription;

    @InjectView(R.id.down)
    ImageButton mDown;

    @InjectView(R.id.up)
    ImageButton mUp;

    public Thermostat(ThermostatModel model) {
        mModel = model;
    }

    @Override
    public void attach(View view) {
        ButterKnife.inject(this, view);

        mTitle.setText(mModel.toString());
        mDescription.setText(mModel.getStatusText());

        mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
            }
        });
        mUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up();
            }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.widget_device_thermostat;
    }

    private void down() {
        String newTemp = String.valueOf(mModel.getTemperature() - 1);
        setState(mModel.getId(), newTemp, mModel.setStatus("temperature", newTemp));
    }

    private void up() {
        String newTemp = String.valueOf(mModel.getTemperature() + 1);
        setState(mModel.getId(), newTemp, mModel.setStatus("temperature", newTemp));
    }
}
