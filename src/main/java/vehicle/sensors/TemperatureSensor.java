package vehicle.sensors;

import java.util.ArrayList;

public class TemperatureSensor {

    private ArrayList<ITemperatureSensorListener> listeners;

    public TemperatureSensor() {
        listeners = new ArrayList<>();
    }

    public void trigger() {
        for (ITemperatureSensorListener listener : listeners) {
            listener.updateTemperature("Temperature updated");
        }
    }

    public void addListener(ITemperatureSensorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ITemperatureSensorListener listener) {
        listeners.remove(listener);
    }
}
