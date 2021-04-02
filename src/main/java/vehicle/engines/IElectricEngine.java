package vehicle.engines;

public interface IElectricEngine {

    int consumption();

    boolean on();

    boolean off();

    void increaseRPM(double deltaRPM, int seconds);

    void decreaseRPM(double deltaRPM, int seconds);
}
