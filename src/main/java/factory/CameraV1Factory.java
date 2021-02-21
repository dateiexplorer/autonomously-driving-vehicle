package factory;

import configuration.Configuration;

public class CameraV1Factory extends Factory {

    public static Object build() {
        return build(Configuration.instance.pathToCameraV1JavaArchive, "CameraV1");
    }
}
