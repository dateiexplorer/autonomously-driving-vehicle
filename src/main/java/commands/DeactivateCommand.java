package commands;

import vehicle.base.KeyReceiverDevice;

public class DeactivateCommand implements ICommand {

    private String password;
    private KeyReceiverDevice device;

    public DeactivateCommand(String password, KeyReceiverDevice device) {
        this.password = password;
        this.device = device;
    }

    @Override
    public void execute() {
        device.receive(password, false);
    }
}
