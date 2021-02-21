package factory;

import configuration.Configuration;

public class CameraV2Factory extends Factory {

    public static Object build() {
        return build(Configuration.instance.pathToCameraV2JavaArchive, "CameraV2");
    }
}
