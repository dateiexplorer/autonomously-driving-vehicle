package vehicle;

import configuration.Configuration;
import power.battery.*;
import events.base.*;
import events.engine.*;
import events.lights.*;
import events.sensors.*;
import vehicle.base.*;
import vehicle.body.*;
import vehicle.engines.*;
import vehicle.interior.*;
import vehicle.lights.*;
import vehicle.sensors.*;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle {

    private BoardComputer boardComputer;
    private boolean activated;
    private List<DoorSensor> doorSensors;

    private Chassis chassis;
    private IElectricEngine engine;
    private List<LEDHeadlight> headlights;
    private List<BrakeLight> brakeLights;
    private List<Indicator> indicators;
    private List<Door> leftDoors;
    private List<Door> rightDoors;
    private List<Bench> benches;
    private List<Wheel> wheels;
    private List<Brake> brakes;
    private List<GPS> gps;
    private List<Object> cameras;
    private List<Object> lidars;
    private List<Battery> batteries;
    private List<UltrasonicSensor> ultrasonicSensors;


    private Vehicle(Builder builder) {
        boardComputer = new BoardComputer(this);
        activated = false;
        this.doorSensors = builder.doorSensors;

        this.chassis = builder.chassis;
        this.engine = builder.engine;
        this.headlights = builder.headlights;
        this.brakeLights = builder.brakeLights;
        this.indicators = builder.indicators;
        this.leftDoors = builder.leftDoors;
        this.rightDoors = builder.rightDoors;
        this.benches = builder.benches;
        this.wheels = builder.wheels;
        this.brakes = builder.brakes;
        this.gps = builder.gps;
        this.cameras = builder.cameras;
        this.lidars = builder.lidars;
        this.batteries = builder.batteries;
        this.ultrasonicSensors = builder.ultrasonicSensors;

        for (Battery b : batteries) {
            b.getTemperatureSensor().addListener(boardComputer);
        }

        for (UltrasonicSensor s : ultrasonicSensors) {
            s.addListener(boardComputer);
        }

        for (DoorSensor s : doorSensors) {
            s.addListener(boardComputer);
        }
    }

    public void running() {
        for (int i = 0; i < engine.consumption(); i++) {
            boolean noEnergy = true;
            for (Battery b : batteries) {
                Cell charged = (Cell) b.getChargedCell();
                if (charged != null) {
                    charged.setEnergy(Cell.DISCHARGED);
                    noEnergy = false;
                    break;
                }
            }

            if (noEnergy) {
                System.out.println("No energy. Please charge your battery!");
            }
        }

        System.out.println("Energy: " + calculateEnergy());
    }

    public int calculateEnergy() {
        int energy = 0;
        for (Battery b : batteries) {
            energy += b.getEnergy();
        }

        return energy;
    }

    public void activate() {
        activated = true;
        System.out.println("Car activated!");
    }

    public void deactivate() {
        activated = false;
        System.out.println("Car deactivated!");
    }

    @Override
    public void startup() {
        boardComputer.post(new EngineOn());
        boardComputer.post(new LEDOn());
        boardComputer.post(new GPSOn());
        boardComputer.post(new GPSConnectedSatellite(118.75));
        boardComputer.post(new CameraOn());
        boardComputer.post(new LidarOn());
        boardComputer.post(new LeftIndicatorOn());
    }

    @Override
    public void move(double deltaRPM, int seconds) {
        boardComputer.post(new LeftIndicatorOff());
        boardComputer.post(new RightIndicatorOff());
        boardComputer.post(new LEDDimmed());
        boardComputer.post(new IncreaseRPM(deltaRPM, seconds));
        boardComputer.post(new BrakeSet(0));
        boardComputer.post(new BrakeLightOff());

        running();
    }

    @Override
    public void leftTurn(double deltaRPM, int seconds) {
        boardComputer.post(new LeftIndicatorOn());
        boardComputer.post(new DecreaseRPM(deltaRPM, seconds));
        boardComputer.post(new BrakeSet(70));
        boardComputer.post(new BrakeLightOn());

        running();
    }

    @Override
    public void rightTurn(double deltaRPM, int seconds) {
        boardComputer.post(new RightIndicatorOn());
        boardComputer.post(new DecreaseRPM(deltaRPM, seconds));
        boardComputer.post(new BrakeSet(70));
        boardComputer.post(new BrakeLightOn());

        running();
    }

    @Override
    public void stop() {
        boardComputer.post(new BrakeSet(100));
        boardComputer.post(new BrakeLightOn());

        running();
    }

    @Override
    public void emergencyStop() {
        boardComputer.post(new BrakeSet(100));
        boardComputer.post(new BrakeLightOn());
        boardComputer.post(new HazardWarningOn());
        boardComputer.post(new EngineOff());
        boardComputer.post(new LEDHighBeam());
        boardComputer.post(new CameraOff());
        boardComputer.post(new LidarOff());

        running();
    }

    @Override
    public void shutdown() {
        boardComputer.post(new BrakeSet(100));
        boardComputer.post(new EngineOff());
        boardComputer.post(new BrakeLightOff());
        boardComputer.post(new LEDOff());
        boardComputer.post(new HazardWarningOff());
        boardComputer.post(new GPSOff());
        boardComputer.post(new CameraOff());
        boardComputer.post(new LidarOff());
    }

    public static class Builder {

        private Chassis chassis;
        private IElectricEngine engine;
        private List<DoorSensor> doorSensors = new ArrayList<>();
        private List<LEDHeadlight> headlights = new ArrayList<>();
        private List<BrakeLight> brakeLights = new ArrayList<>();
        private List<Indicator> indicators = new ArrayList<>();
        private List<Door> leftDoors = new ArrayList<>();
        private List<Door> rightDoors = new ArrayList<>();
        private List<Bench> benches = new ArrayList<>();
        private List<Wheel> wheels = new ArrayList<>();
        private List<Brake> brakes = new ArrayList<>();
        private List<GPS> gps = new ArrayList<>();
        private List<Object> cameras = new ArrayList<>();
        private List<Object> lidars = new ArrayList<>();
        private List<Battery> batteries = new ArrayList<>();
        private List<UltrasonicSensor> ultrasonicSensors = new ArrayList<>();

        public Builder chassis() {
            chassis = new Chassis();
            return this;
        }

        public Builder engine() {
            engine = Configuration.instance.engine;
            return this;
        }

        public Builder headLights(int number) {
            for (int i = 0; i < number; i++) {
                headlights.add(new LEDHeadlight());
            }

            return this;
        }

        public Builder brakeLights(int number) {
            for (int i = 0; i < number; i++) {
                brakeLights.add(new BrakeLight());
            }

            return this;
        }

        public Builder indicators(int number) {
            for (int i = 0; i < number; i++) {
                indicators.add(new Indicator());
            }

            return this;
        }

        public Builder leftDoors(int number) {
            for (int i = 0; i < number; i++) {
                leftDoors.add(new Door());
            }

            doorSensors.add(new DoorSensor(DoorSensorPosition.LEFT));
            return this;
        }

        public Builder rightDoors(int number) {
            for (int i = 0; i < number; i++) {
                rightDoors.add(new Door());
            }

            doorSensors.add(new DoorSensor(DoorSensorPosition.RIGHT));
            return this;
        }

        public Builder benches(int number) {
            for (int i = 0; i < number; i++) {
                benches.add(new Bench());
            }

            return this;
        }

        public Builder wheels(int number) {
            for (int i = 0; i < number; i++) {
                wheels.add(new Wheel());
            }

            return this;
        }

        public Builder brakes(int number) {
            for (int i = 0; i < number; i++) {
                brakes.add(new Brake());
            }

            return this;
        }

        public Builder gps(int number) {
            for (int i = 0; i < number; i++) {
                gps.add(new GPS());
            }

            return this;
        }

        public Builder cameras(int number) {
            for (int i = 0; i < number; i++) {
                cameras.add(Configuration.instance.cameraFactory.build());
            }

            return this;
        }

        public Builder lidars(int number) {
            for (int i = 0; i < number; i++) {
                lidars.add(Configuration.instance.lidarFactory.build());
            }

            return this;
        }

        public Builder batteries(int number) {
            for (int i = 0; i < number; i++) {
                batteries.add(buildBattery());
            }

            return this;
        }

        private Battery buildBattery() {
            Battery battery = new Battery();
            for (int i = 0; i < 32; i++) {
                MainCell mainCell = new MainCell();
                for (int j = 0; j < 8; j++) {
                    SubCell subCell = new SubCell();
                    for (int k = 0; k < 2; k++) {
                        Cell cell = new Cell();
                        subCell.addBattery(cell);
                    }

                    mainCell.addBattery(subCell);
                }

                battery.addBattery(mainCell);
            }

            return battery;
        }

        public Builder ultrasonicSensors(int number) {
            for (int i = 0; i < number; i++) {
                ultrasonicSensors.add(new UltrasonicSensor());
            }

            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }

    }

    // Getter and setter
    public BoardComputer getBoardComputer() {
        return boardComputer;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public List<DoorSensor> getDoorSensors() {
        return doorSensors;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public IElectricEngine getEngine() {
        return engine;
    }

    public List<LEDHeadlight> getHeadlights() {
        return headlights;
    }

    public List<BrakeLight> getBrakeLights() {
        return brakeLights;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public List<Door> getLeftDoors() {
        return leftDoors;
    }

    public List<Door> getRightDoors() {
        return rightDoors;
    }

    public List<Bench> getBenches() {
        return benches;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public List<Brake> getBrakes() {
        return brakes;
    }

    public List<GPS> getGPS() {
        return gps;
    }

    public List<Object> getCameras() {
        return cameras;
    }

    public List<Object> getLidars() {
        return lidars;
    }

    public List<Battery> getBatteries() {
        return batteries;
    }

    public List<UltrasonicSensor> getUltrasonicSensors() {
        return ultrasonicSensors;
    }
}
