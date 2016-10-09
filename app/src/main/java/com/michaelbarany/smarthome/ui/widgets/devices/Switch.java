package com.michaelbarany.smarthome.ui.widgets.devices;

import android.view.View;
import android.widget.TextView;

import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.models.SwitchModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Switch extends AbstractDevice {
    private final SwitchModel mModel;

    @InjectView(R.id.title)
    TextView mTitle;

    @InjectView(R.id.description)
    TextView mDescription;

    @InjectView(R.id.switch1)
    android.widget.Switch mSwitch;

    public Switch(SwitchModel model) {
        mModel = model;
    }

    @Override
    public void attach(View view) {
        ButterKnife.inject(this, view);

        mTitle.setText(mModel.toString());
        mDescription.setText(mModel.getStatusText());

        mSwitch.setChecked(mModel.getStatus() != 0);
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((android.widget.Switch) v).isChecked();

                String state = isChecked ? "on" : "off";
                setState(mModel.getId(), state, mModel.setStatus(isChecked ? 1 : 0));
            }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.widget_device_switch;
    }
}
