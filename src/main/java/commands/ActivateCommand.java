package commands;

import vehicle.base.KeyReceiverDevice;

public class ActivateCommand implements ICommand {

    private String password;
    private KeyReceiverDevice device;

    public ActivateCommand(String password, KeyReceiverDevice device) {
        this.password = password;
        this.device = device;
    }

    @Override
    public void execute() {
        device.receive(password, true);
    }
}
