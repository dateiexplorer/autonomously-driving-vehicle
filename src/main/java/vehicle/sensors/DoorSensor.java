package vehicle.sensors;

import commands.ICommand;

import java.util.ArrayList;

public class DoorSensor {

    private DoorSensorPosition position;
    private ICommand command;
    private ArrayList<IDoorSensorListener> listeners;

    public DoorSensor(DoorSensorPosition position) {
        this.position = position;
        listeners = new ArrayList<>();
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void trigger() {
        command.execute();

        for (IDoorSensorListener listener : listeners) {
            listener.updateDoor("Doors " + position.name().toLowerCase() + " updated", position);
        }
    }

    public void addListener(IDoorSensorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IDoorSensorListener listener) {
        listeners.remove(listener);
    }
}
