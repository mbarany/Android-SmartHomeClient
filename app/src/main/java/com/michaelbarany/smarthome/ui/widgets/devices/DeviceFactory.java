package com.michaelbarany.smarthome.ui.widgets.devices;

import com.michaelbarany.smarthome.api.models.AbstractDeviceModel;
import com.michaelbarany.smarthome.api.models.DimmableSwitchModel;
import com.michaelbarany.smarthome.api.models.SwitchModel;
import com.michaelbarany.smarthome.api.models.ThermostatModel;

public class DeviceFactory {
    public static AbstractDevice create(AbstractDeviceModel model) {
        if (model instanceof SwitchModel) {
            return new Switch((SwitchModel) model);
        } else if (model instanceof DimmableSwitchModel) {
            return new DimmableSwitch((DimmableSwitchModel) model);
        } else if (model instanceof ThermostatModel) {
            return new Thermostat((ThermostatModel) model);
        }
        throw new IllegalArgumentException("Unknown model!");
    }
}
