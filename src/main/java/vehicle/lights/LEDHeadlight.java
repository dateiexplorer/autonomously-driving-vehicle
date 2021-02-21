package vehicle.lights;

public class LEDHeadlight {

    private boolean isOn;
    private boolean isDim;

    public boolean on() {
        isOn = true;
        return isOn;
    }

    public boolean off() {
        isOn = false;
        return isOn;
    }

    public boolean dim() {
        isDim = true;
        return isDim;
    }

    public boolean highBeam() {
        isDim = false;
        return isDim;
    }
}
