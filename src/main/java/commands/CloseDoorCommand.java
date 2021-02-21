package commands;

import vehicle.body.Door;

import java.util.List;

public class CloseDoorCommand implements ICommand {

    private List<Door> doors;

    public CloseDoorCommand(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public void execute() {
        for (Door d : doors) {
            d.getServoMotor().close();
        }
    }
}
