package factory;

import configuration.Configuration;

public class LidarXTFactory extends Factory {

    public static Object build() {
        return build(Configuration.instance.pathToLidarXTJavaArchive, "LidarXT");
    }
}
