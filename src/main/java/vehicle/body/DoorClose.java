package vehicle.body;

public class DoorClose implements DoorState {

    @Override
    public void toggle(Door door) {
        door.setState(new DoorOpen());
        System.out.println("Door: State - Open");
    }
}
