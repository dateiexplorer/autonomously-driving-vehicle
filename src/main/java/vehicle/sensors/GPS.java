package vehicle.sensors;

public class GPS {

    private boolean isOn;
    private double frequency;

    public boolean on() {
        isOn = true;
        return isOn;
    }

    public boolean off() {
        isOn = false;
        return isOn;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }
}
