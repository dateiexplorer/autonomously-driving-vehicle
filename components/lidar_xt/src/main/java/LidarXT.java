public class LidarXT {

    private static LidarXT instance = new LidarXT();

    // Port
    public Port port;

    private String version = "XT";

    private boolean isOn;

    private LidarXT() {
        port = new Port();
    }

    public static LidarXT getInstance() {
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

    public class Port implements ILidarXT {

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
