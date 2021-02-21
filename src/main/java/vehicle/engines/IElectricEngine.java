package vehicle.engines;

import vehicle.IVehicle;
import vehicle.Vehicle;

public interface IElectricEngine {

    int consumption();

    boolean on();

    boolean off();

    void increaseRPM(double deltaRPM, int seconds);

    void decreaseRPM(double deltaRPM, int seconds);
}
