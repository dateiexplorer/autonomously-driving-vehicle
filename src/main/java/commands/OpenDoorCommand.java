package commands;

import vehicle.body.Door;

import java.util.List;

public class OpenDoorCommand implements ICommand {

    private List<Door> doors;

    public OpenDoorCommand(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public void execute() {
        for (Door d : doors) {
            d.getServoMotor().open();
        }
    }
}
