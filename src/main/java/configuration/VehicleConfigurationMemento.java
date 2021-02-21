package configuration;

import java.util.ArrayList;
import java.util.List;

public class VehicleConfigurationMemento {

    private List<VehicleConfigurationOption> options;

    public VehicleConfigurationMemento(VehicleConfiguration configuration) {
        this.options = new ArrayList<>();

        List<VehicleConfigurationOption> options = configuration.getOptions();
        for (VehicleConfigurationOption o : options) {
            this.options.add((VehicleConfigurationOption) o.clone());
        }
    }

    public List<VehicleConfigurationOption> getOptions() {
        return options;
    }
}
