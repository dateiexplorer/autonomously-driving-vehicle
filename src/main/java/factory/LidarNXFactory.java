package factory;

import configuration.Configuration;

public class LidarNXFactory extends Factory {

    public static Object build() {
        return build(Configuration.instance.pathToLidarNXJavaArchive, "LidarNX");
    }
}
