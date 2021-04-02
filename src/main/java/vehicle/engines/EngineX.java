package vehicle.engines;

public class EngineX implements IElectricEngine {

    private boolean isOn;
    private double rpm;

    @Override
    public int consumption() {
        return 4;
    }

    @Override
    public boolean on() {
        isOn = true;
        return isOn;
    }

    @Override
    public boolean off() {
        isOn = false;
        return isOn;
    }

    @Override
    public void increaseRPM(double deltaRPM, int seconds) {
        this.rpm += deltaRPM;
    }

    @Override
    public void decreaseRPM(double deltaRPM, int seconds) {
        this.rpm -= deltaRPM;
    }
}
