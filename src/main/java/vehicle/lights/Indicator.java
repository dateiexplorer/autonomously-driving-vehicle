package vehicle.lights;

public class Indicator {

    private boolean isOn;

    public boolean on() {
        isOn = true;
        return isOn;
    }

    public boolean off() {
        isOn = false;
        return isOn;
    }
}
