package events.sensors;

public class GPSConnectedSatellite {

    private double frequency;

    public GPSConnectedSatellite(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Event: GPS - ConnectedSatellite(" + frequency + ")";
    }

    public double getFrequency() {
        return frequency;
    }
}
