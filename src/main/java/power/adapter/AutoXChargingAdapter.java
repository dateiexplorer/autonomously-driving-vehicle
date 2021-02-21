package power.adapter;

import power.ChargingStation;
import power.IAutoXChargingDevice;
import vehicle.Vehicle;

public class AutoXChargingAdapter extends ChargingStation implements IAutoXChargingDevice {

    @Override
    public void charging3pole(Vehicle vehicle) {
        System.out.println("using autoX charging adapter | ");
        charging2pole(vehicle);
    }
}
