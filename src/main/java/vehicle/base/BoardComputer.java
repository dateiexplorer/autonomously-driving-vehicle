package vehicle.base;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import events.Subscriber;
import events.base.BrakeSet;
import events.engine.DecreaseRPM;
import events.engine.EngineOff;
import events.engine.EngineOn;
import events.engine.IncreaseRPM;
import events.lights.*;
import events.sensors.*;
import security.AES;
import vehicle.Vehicle;
import vehicle.body.Door;
import vehicle.sensors.DoorSensorPosition;
import vehicle.sensors.IDoorSensorListener;
import vehicle.sensors.ITemperatureSensorListener;
import vehicle.sensors.IUltrasonicSensorListener;

import java.lang.reflect.Method;

public class BoardComputer extends Subscriber implements ITemperatureSensorListener, IUltrasonicSensorListener, IDoorSensorListener {

    private EventBus eventBus;
    private Vehicle vehicle;

    private KeyReceiverDevice keyReceiverDevice;

    public BoardComputer(Vehicle vehicle) {
        eventBus = new EventBus("Vehicle");
        eventBus.register(this);
        this.vehicle = vehicle;

        keyReceiverDevice = new KeyReceiverDevice(this);
    }

    public void post(Object object) {
        eventBus.post(object);
    }

    public void decrypt(String password, boolean activate) {
        String encrypted = AES.decrypt(password, Configuration.instance.secretKey);
        if (encrypted.equals("AutoX23")) {
            if (activate) {
                vehicle.activate();
            } else {
                vehicle.deactivate();
            }
        } else {
            System.out.println("This is not the key to listen to. Ignoring!");
        }
    }

    @Subscribe
    public void receive(EngineOn event) {
        System.out.println(event);

        vehicle.getEngine().on();
    }

    @Subscribe
    public void receive(EngineOff event) {
        System.out.println(event);

        vehicle.getEngine().off();
    }

    @Subscribe
    public void receive(IncreaseRPM event) {
        System.out.println(event);

        vehicle.getEngine().increaseRPM(event.getDeltaRPM(), event.getSeconds());
    }

    @Subscribe
    public void receive(DecreaseRPM event) {
        System.out.println(event);

        vehicle.getEngine().decreaseRPM(event.getDeltaRPM(), event.getSeconds());
    }

    @Subscribe
    public void receive(LEDOn event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getHeadlights().size(); i++) {
            vehicle.getHeadlights().get(i).on();
        }
    }

    @Subscribe
    public void receive(LEDOff event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getHeadlights().size(); i++) {
            vehicle.getHeadlights().get(i).off();
        }
    }

    @Subscribe
    public void receive(LEDDimmed event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getHeadlights().size(); i++) {
            vehicle.getHeadlights().get(i).dim();
        }
    }

    @Subscribe
    public void receive(LEDHighBeam event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getHeadlights().size(); i++) {
            vehicle.getHeadlights().get(i).highBeam();
        }
    }

    @Subscribe
    public void receive(BrakeLightOn event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getBrakeLights().size(); i++) {
            vehicle.getBrakeLights().get(i).on();
        }
    }

    @Subscribe
    public void receive(BrakeLightOff event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getBrakeLights().size(); i++) {
            vehicle.getBrakeLights().get(i).off();
        }
    }

    @Subscribe
    public void receive(LeftIndicatorOn event) {
        System.out.println(event);

        for (int i = vehicle.getIndicators().size() / 2; i < vehicle.getIndicators().size(); i++) {
            vehicle.getIndicators().get(i).on();
        }
    }

    @Subscribe
    public void receive(LeftIndicatorOff event) {
        System.out.println(event);

        for (int i = vehicle.getIndicators().size() / 2; i < vehicle.getIndicators().size(); i++) {
            vehicle.getIndicators().get(i).off();
        }
    }

    @Subscribe
    public void receive(RightIndicatorOn event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getIndicators().size() / 2; i++) {
            vehicle.getIndicators().get(i).on();
        }
    }

    @Subscribe
    public void receive(RightIndicatorOff event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getIndicators().size() / 2; i++) {
            vehicle.getIndicators().get(i).off();
        }
    }

    @Subscribe
    public void receive(HazardWarningOn event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getIndicators().size(); i++) {
            vehicle.getIndicators().get(i).on();
        }
    }

    @Subscribe
    public void receive(HazardWarningOff event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getIndicators().size(); i++) {
            vehicle.getIndicators().get(i).off();
        }
    }

    @Subscribe
    public void receive(BrakeSet event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getBrakes().size(); i++) {
            vehicle.getBrakes().get(i).setPercentage(event.getPercentage());
        }
    }

    @Subscribe
    public void receive(GPSOn event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getGPS().size(); i++) {
            vehicle.getGPS().get(i).on();
        }
    }

    @Subscribe
    public void receive(GPSOff event) {
        System.out.println(event);

        for (int i = 0; i < vehicle.getGPS().size(); i++) {
            vehicle.getGPS().get(i).off();
        }
    }

    @Subscribe
    public void receive(GPSConnectedSatellite event) {
        System.out.println(event);
        for (int i = 0; i < vehicle.getGPS().size(); i++) {
            vehicle.getGPS().get(i).setFrequency(event.getFrequency());
        }
    }

    @Subscribe
    public void receive(CameraOn event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getCameras().size(); i++) {
                Method method = vehicle.getCameras().get(i).getClass().getDeclaredMethod("on");
                boolean isOn = (boolean) method.invoke(vehicle.getCameras().get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(CameraOff event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getCameras().size(); i++) {
                Method method = vehicle.getCameras().get(i).getClass().getDeclaredMethod("off");
                boolean isOn = (boolean) method.invoke(vehicle.getCameras().get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(LidarOn event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getLidars().size(); i++) {
                Method method = vehicle.getLidars().get(i).getClass().getDeclaredMethod("on");
                boolean isOn = (boolean) method.invoke(vehicle.getLidars().get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(LidarOff event) {
        try {
            System.out.println(event);

            for (int i = 0; i < vehicle.getLidars().size(); i++) {
                Method method = vehicle.getLidars().get(i).getClass().getDeclaredMethod("off");
                boolean isOn = (boolean) method.invoke(vehicle.getLidars().get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTemperature(String temperature) {
        System.out.println("BoardComputer - TemperatureSensor: " + temperature);
    }

    @Override
    public void updateDistance(String distance) {
        System.out.println("BoardComputer - UltrasonicSensor: " + distance);
    }

    @Override
    public void updateDoor(String status, DoorSensorPosition position) {
        System.out.println("BoardComputer - DoorSensor: " + status);
        switch (position) {
            case LEFT:
                for (Door d : vehicle.getLeftDoors()) {
                    d.toggle();
                }
                break;
            case RIGHT:
                for (Door d : vehicle.getRightDoors()) {
                    d.toggle();
                }
                break;
        }
    }

    // Getter and setter
    public KeyReceiverDevice getKeyReceiverDevice() {
        return keyReceiverDevice;
    }

}
