package vehicle.body;

public class Door {

    private DoorState doorState;
    private ServoMotor servoMotor;

    public Door() {
        doorState = new DoorClose();
        servoMotor = new ServoMotor();
    }

    public void toggle() {
        doorState.toggle(this);
    }

    // Getter and setter

    public void setState(DoorState doorState) {
        this.doorState = doorState;
    }

    public DoorState getState() {
        return doorState;
    }

    public ServoMotor getServoMotor() {
        return servoMotor;
    }
}
