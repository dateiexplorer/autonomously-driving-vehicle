package factory;

import configuration.Configuration;

public class LidarNGFactory extends Factory {

    public static Object build() {
        return build(Configuration.instance.pathToLidarNGJavaArchive, "LidarNG");
    }
}
