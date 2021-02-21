package vehicle.engines;

import power.battery.Battery;
import power.battery.Cell;
import vehicle.Vehicle;

public class EngineNG implements IElectricEngine {

    private boolean isOn;
    private double rpm;

    @Override
    public int consumption() {
        return 4;
    }

    @Override
    public boolean on() {
        isOn = true;
        return isOn;
    }

    @Override
    public boolean off() {
        isOn = false;
        return isOn;
    }

    @Override
    public void increaseRPM(double deltaRPM, int seconds) {
        this.rpm += deltaRPM;
    }

    @Override
    public void decreaseRPM(double deltaRPM, int seconds) {
        this.rpm -= deltaRPM;
    }
}
