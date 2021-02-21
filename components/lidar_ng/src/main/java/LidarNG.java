public class LidarNG {

    private static LidarNG instance = new LidarNG();

    // Port
    public Port port;

    private String version = "NG";

    private boolean isOn;

    private LidarNG() {
        port = new Port();
    }

    public static LidarNG getInstance() {
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

    public class Port implements ILidarNG {

        @Override
        public String version() {
            return "version: " + version;
        }

        @Override
        public void scan() {
            System.out.println("Scanning with Lidar" + version);
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
