package configuration;

import factory.CameraV1Factory;
import factory.LidarNGFactory;
import vehicle.engines.EngineNG;
import vehicle.engines.IElectricEngine;

public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;

    public String pathToCameraV1JavaArchive = commonPathToJavaArchive + "camera_v1" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera_v1.jar";
    public String pathToCameraV2JavaArchive = commonPathToJavaArchive + "camera_v2" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera_v2.jar";
    public String pathToLidarNGJavaArchive = commonPathToJavaArchive + "lidar_ng" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "lidar_ng.jar";
    public String pathToLidarXTJavaArchive = commonPathToJavaArchive + "lidar_xt" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "lidar_xt.jar";

    public String secretKey = "DHAutoX";

    public IElectricEngine engine = new EngineNG();
    public CameraV1Factory cameraFactory;
    public LidarNGFactory lidarFactory;
}
