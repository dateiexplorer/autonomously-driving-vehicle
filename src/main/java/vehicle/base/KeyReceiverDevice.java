package vehicle.base;

public class KeyReceiverDevice {

    private BoardComputer boardComputer;

    public KeyReceiverDevice(BoardComputer boardComputer) {
        this.boardComputer = boardComputer;
    }

    public void receive(String password, boolean activate) {
        boardComputer.decrypt(password, activate);
    }
}
