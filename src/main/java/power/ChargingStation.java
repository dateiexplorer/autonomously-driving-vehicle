package power;

import power.battery.Battery;
import power.battery.Cell;
import vehicle.Vehicle;

public class ChargingStation {

    public void charging2pole(Vehicle vehicle) {
        if (vehicle.getBatteries().isEmpty()) {
            return;
        }

        System.out.println("Charging...");
        for (Battery b : vehicle.getBatteries()) {
            Cell discharged = (Cell) b.getDischargedCell();
            if (discharged != null) {
                discharged.setEnergy(Cell.CHARGED);
                System.out.println(" (energy = " + vehicle.calculateEnergy() + ")");
                return;
            }
        }

        System.out.println("Charging completed! (energy = " + vehicle.calculateEnergy() + ")");
    }
}
