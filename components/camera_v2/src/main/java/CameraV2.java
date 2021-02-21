public class CameraV2 {

    private static CameraV2 instance = new CameraV2();

    // Port
    public Port port;

    private String version = "V2";

    private boolean isOn;

    private CameraV2() {
        port = new Port();
    }

    public static CameraV2 getInstance() {
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

    public class Port implements ICameraV2 {

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
