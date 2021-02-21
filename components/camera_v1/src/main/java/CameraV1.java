public class CameraV1 {

    private static CameraV1 instance = new CameraV1();

    // Port
    public Port port;

    private String version = "V1";

    private boolean isOn;

    private CameraV1() {
        port = new Port();
    }

    public static CameraV1 getInstance() {
        return instance;
    }

    private boolean innerOn() {
        this.isOn = true;
        return isOn;
    }

    private boolean innerOff() {
        this.isOn = false;
        return isOn;
    }

    public class Port implements ICameraV1 {

        @Override
        public String version() {
            return "version: " + version;
        }

        @Override
        public void snap() {
            System.out.println("Take snap with Camera" + version);
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }

}
