package power.battery;

import vehicle.sensors.TemperatureSensor;

import java.util.ArrayList;
import java.util.List;

public class Battery {

    protected Battery parent;
    protected List<Battery> batteries = new ArrayList<>();
    protected TemperatureSensor temperatureSensor;

    public Battery() {
        temperatureSensor = new TemperatureSensor();
    }

    public void addBattery(Battery battery) {
        batteries.add(battery);
    }

    public Battery getDischargedCell() {
        if (batteries.isEmpty()) {
            return null;
        }

        for (Battery b : batteries) {
            Battery discharged = b.getDischargedCell();
            if (discharged != null) {
                return discharged;
            }
        }

        return null;
    }

    public Battery getChargedCell() {
        if (batteries.isEmpty()) {
            return null;
        }

        for (Battery b : batteries) {
            Battery charged = b.getChargedCell();
            if (charged != null) {
                return charged;
            }
        }

        return null;
    }

    public int getEnergy() {
        int energy = 0;
        if (batteries.isEmpty()) {
            return energy;
        }

        for (Battery b : batteries) {
            energy += b.getEnergy();
        }

        return energy;
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }
}
