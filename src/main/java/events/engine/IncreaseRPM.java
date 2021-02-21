package events.engine;

public class IncreaseRPM {

    private double deltaRPM;
    private int seconds;

    public IncreaseRPM(double deltaRPM, int seconds) {
        this.deltaRPM = deltaRPM;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Event: Engine - IncreaseRPM(" + deltaRPM + ", " + seconds + ")";
    }

    public double getDeltaRPM() {
        return deltaRPM;
    }

    public int getSeconds() {
        return seconds;
    }
}
