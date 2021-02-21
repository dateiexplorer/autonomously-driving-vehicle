package security;

import commands.ICommand;
import configuration.Configuration;

public class Key {

    private ICommand command;
    private String password;

    public Key() {
        password = AES.encrypt("AutoX23", Configuration.instance.secretKey);
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void pushButton() {
        command.execute();
    }

    public String getPassword() {
        return password;
    }

}
