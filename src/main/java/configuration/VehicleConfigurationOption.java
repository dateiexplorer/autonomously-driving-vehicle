package configuration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VehicleConfigurationOption implements Cloneable {

    private String parameter;
    private List<String> allowedValues;
    private String currentValue;

    public VehicleConfigurationOption(String parameter, String defaultValue, String... allowedValues) {
        this.parameter = parameter;
        if (!Arrays.stream(allowedValues).anyMatch(s -> s.equals(defaultValue))) {
            throw new RuntimeException("Invalid default value!");
        }

        this.currentValue = defaultValue;
        this.allowedValues = Arrays.asList(allowedValues.clone());

    }

    public VehicleConfigurationOption(String parameter, String currentValue, List<String> allowedValues) {
        this.parameter = parameter;
        this.currentValue = currentValue;
        this.allowedValues = allowedValues;
    }

    public void print() {
        System.out.println(parameter + ": " + currentValue);
    }

    public boolean setParameter(String currentValue) {
        if (allowedValues.contains(currentValue)) {
            this.currentValue = currentValue;
            return true;
        }

        return false;
    }

    public String getAllowedValuesAsString() {
        String values = "";
        Iterator iterator = allowedValues.iterator();

        while (iterator.hasNext()) {
            values += iterator.next();
            if (iterator.hasNext()) {
                values += " ";
            }
        }

        return values;
    }

    @Override
    protected Object clone() {
        return new VehicleConfigurationOption(parameter, String.valueOf(currentValue), allowedValues);
    }

    public String getParameter() {
        return parameter;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public String getCurrentValue() {
        return currentValue;
    }
}
