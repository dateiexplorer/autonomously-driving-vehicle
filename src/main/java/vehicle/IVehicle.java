package vehicle;

public interface IVehicle {

    void startup();

    void move(double deltaRPM, int seconds);

    void leftTurn(double deltaRPM, int seconds);

    void rightTurn(double deltaRPM, int seconds);

    void stop();

    void emergencyStop();

    void shutdown();
}
