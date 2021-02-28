package events.engine;

public class DecreaseRPM {

    private double deltaRPM;
    private int seconds;

    public DecreaseRPM(double deltaRPM, int seconds) {
        this.deltaRPM = deltaRPM;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Event: Engine - DecreaseRPM(" + deltaRPM + ", " + seconds + ")";
    }

    public double getDeltaRPM() {
        return deltaRPM;
    }

    public int getSeconds() {
        return seconds;
    }
}
