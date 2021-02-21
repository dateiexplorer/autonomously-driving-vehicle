package configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleConfiguration {

    private List<VehicleConfigurationOption> options;

    private Scanner scanner;
    private VehicleConfigurationCareTaker careTaker;

    public VehicleConfiguration() {
        options = new ArrayList<>() {{
            add(new VehicleConfigurationOption("rejectDrunkenPassenger", "true",
                    "true", "false"));
            add(new VehicleConfigurationOption("stopByPoliceRequest", "true",
                    "true", "false"));
            add(new VehicleConfigurationOption("allowDriveByNight", "true",
                    "true", "false"));
            add(new VehicleConfigurationOption("behaviorWithNaggingPassengers",
                    "stopAndWaitForExcuse", "doNothing", "stopAndWaitForExcuse"));
            add(new VehicleConfigurationOption("musicDuringDrive", "ac/dc",
                    "ac/dc", "helene fischer"));
        }};

        scanner = new Scanner(System.in);
        careTaker = new VehicleConfigurationCareTaker();
    }

    public void showMenu() {
        System.out.println("--- Menu ---");
        System.out.println("(0) print");
        System.out.println("(1) set parameter");
        System.out.println("(2) undo");
        System.out.println("(3) exit");
        System.out.print("Enter the id of an action: ");

        int id = scanner.nextInt();
        switch (id) {
            case 0:
                print();
                showMenu();
                break;
            case 1:
                setParameter();
                showMenu();
                break;
            case 2:
                undo();
                showMenu();
                break;
            case 3:
            default:
                System.exit(0);
        }
    }

    public void print() {
        System.out.println("--- print ---");
        for (VehicleConfigurationOption o : options) {
            o.print();
        }
    }

    public void setParameter() {
        careTaker.setMemento(save());

        System.out.println("--- set parameter ---");
        // Print all options.
        for (int i = 0; i < options.size(); i++) {
            VehicleConfigurationOption o = options.get(i);
            System.out.printf("(%d) %s: %s%n", i, o.getParameter(), o.getCurrentValue());
        }

        int index = -1;
        do {
            System.out.print("Enter the id of a parameter you want to change: ");
            index = scanner.nextInt();
        } while (!(index > -1 && index < options.size()));

        VehicleConfigurationOption change = options.get(index);

        boolean valid = false;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(change.getParameter() + " | current " + change.getCurrentValue() + " | allowed " +
                    change.getAllowedValuesAsString() + " > ");
        } while (!change.setParameter(scanner.nextLine()));

        System.out.println("Configuration successfully updated!");
    }

    public void undo() {
        if (careTaker.getMemento() == null) {
            System.out.println("No changes were made.");
            return;
        }

        restore(careTaker.getMemento());
        System.out.println("Undo last modify.");
    }

    private VehicleConfigurationMemento save() {
        return new VehicleConfigurationMemento(this);
    }

    private void restore(VehicleConfigurationMemento memento) {
        // Save last memento for toggle.
        careTaker.setMemento(save());
        this.options = memento.getOptions();
    }

    public List<VehicleConfigurationOption> getOptions() {
        return options;
    }
}
