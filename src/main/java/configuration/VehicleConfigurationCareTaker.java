package configuration;

public class VehicleConfigurationCareTaker {

    private VehicleConfigurationMemento memento;

    public void setMemento(VehicleConfigurationMemento memento) {
        this.memento = memento;
    }

    public VehicleConfigurationMemento getMemento() {
        return memento;
    }
}
