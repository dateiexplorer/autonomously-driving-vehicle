package vehicle.body;

public class DoorOpen implements DoorState {

    @Override
    public void toggle(Door door) {
        door.setState(new DoorClose());
        System.out.println("Door: State - Close");
    }
}
