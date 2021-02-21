package power.battery;

public class Cell extends SubCell {

    public static final byte CHARGED = 1;
    public static final byte DISCHARGED = 0;

    private byte energy;

    public Cell() {
        this.energy = CHARGED;
    }

    @Override
    public Battery getDischargedCell() {
        return energy == DISCHARGED ? this : null;
    }

    @Override
    public Battery getChargedCell() {
        return energy == CHARGED ? this : null;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(byte energy) {
        this.energy = energy;
    }
}
