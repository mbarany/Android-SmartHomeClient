package com.michaelbarany.smarthome.ui.widgets.devices;

import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.michaelbarany.smarthome.R;
import com.michaelbarany.smarthome.api.models.DimmableSwitchModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DimmableSwitch extends AbstractDevice {
    private final DimmableSwitchModel mModel;

    @InjectView(R.id.title)
    TextView mTitle;

    @InjectView(R.id.description)
    TextView mDescription;

    @InjectView(R.id.switch1)
    Switch mSwitch;

    @InjectView(R.id.slider)
    SeekBar mSlider;

    public DimmableSwitch(DimmableSwitchModel model) {
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
                boolean isChecked = ((Switch) v).isChecked();

                mSlider.setProgress(isChecked ? 100 : 0);
                String state = isChecked ? "on" : "off";
                setState(mModel.getId(), state, mModel.setStatus(isChecked ? 100 : 0));
            }
        });

        mSlider.setProgress(mModel.getStatus());
        mSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {
              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
                  int status = seekBar.getProgress();
                  mSwitch.setChecked(status > 0);
                  String state = Integer.toString(status);
                  setState(mModel.getId(), state, mModel.setStatus(status));
              }
        });
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.widget_device_dimmable_switch;
    }
}
