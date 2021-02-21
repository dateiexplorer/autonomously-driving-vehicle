package vehicle.sensors;

public interface IDoorSensorListener {

    void updateDoor(String status, DoorSensorPosition position);
}
