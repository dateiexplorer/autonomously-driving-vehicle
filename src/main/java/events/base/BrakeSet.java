package events.base;

public class BrakeSet {

    public int percentage;

    public BrakeSet(int percentage) {
        setPercentage(percentage);
    }

    private void setPercentage(int percentage) {
        this.percentage = percentage < 0 ? 0 : percentage > 100 ? 100 : percentage;
    }

    @Override
    public String toString() {
        return "Event: Brake - Set(" + percentage + ")";
    }

    public int getPercentage() {
        return percentage;
    }
}
