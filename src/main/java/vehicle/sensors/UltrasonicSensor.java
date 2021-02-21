package vehicle.sensors;

import java.util.ArrayList;

public class UltrasonicSensor {

    private ArrayList<IUltrasonicSensorListener> listeners;

    public UltrasonicSensor() {
        listeners = new ArrayList<>();
    }

    public void trigger() {
        for (IUltrasonicSensorListener listener : listeners) {
            listener.updateDistance("Distance updated");
        }
    }

    public void addListener(IUltrasonicSensorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IUltrasonicSensorListener listener) {
        listeners.remove(listener);
    }
}
