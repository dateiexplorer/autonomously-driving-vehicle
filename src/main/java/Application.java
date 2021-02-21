import commands.CloseDoorCommand;
import commands.OpenDoorCommand;
import configuration.VehicleConfiguration;
import power.adapter.AutoXChargingAdapter;
import commands.ActivateCommand;
import commands.DeactivateCommand;
import security.Key;
import vehicle.Vehicle;

public class Application {

    public static void main(String[] args) {
        Vehicle autoX = new Vehicle.Builder().chassis().engine().headLights(2).brakeLights(2).indicators(4).leftDoors(2)
                .rightDoors(2).benches(6).wheels(4).brakes(8).gps(2).cameras(2).lidars(4).batteries(4)
                .ultrasonicSensors(8).build();

        if (args.length == 1 && args[0].equals("-config")) {
            new VehicleConfiguration().showMenu();
        }

        Key key = new Key();

        System.out.println("--- Key push button ---");

        key.setCommand(new ActivateCommand(key.getPassword(), autoX.getBoardComputer().getKeyReceiverDevice()));
        key.pushButton();

        System.out.println();
        System.out.println("--- Trigger door sensors ---");

        autoX.getDoorSensors().get(0).setCommand(new OpenDoorCommand(autoX.getLeftDoors()));
        autoX.getDoorSensors().get(0).trigger();

        autoX.getDoorSensors().get(0).setCommand(new CloseDoorCommand(autoX.getLeftDoors()));
        autoX.getDoorSensors().get(0).trigger();

        System.out.println();
        System.out.println("--- Vehicle ---");

        autoX.startup();
        autoX.move(70, 3);
        autoX.leftTurn(30, 2);
        autoX.rightTurn(30, 2);
        autoX.stop();
        autoX.emergencyStop();
        autoX.shutdown();

        System.out.println();
        System.out.println("--- Trigger battery temperature sensors ---");
        autoX.getBatteries().get(0).getTemperatureSensor().trigger();

        System.out.println();
        System.out.println("--- Trigger ultrasonic sensors ---");
        autoX.getUltrasonicSensors().get(0).trigger();

        System.out.println();
        System.out.println("--- Key push button ---");

        key.setCommand(new DeactivateCommand(key.getPassword(), autoX.getBoardComputer().getKeyReceiverDevice()));
        key.pushButton();

        System.out.println();
        System.out.println("--- Charging car ---");

        AutoXChargingAdapter adapter = new AutoXChargingAdapter();
        adapter.charging3pole(autoX);

    }
}
