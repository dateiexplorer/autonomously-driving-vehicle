package factory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Factory {

    public static Object build(String componentPath, String component) {
        Object port = null;

        try {
            // Get jar file from componentPath
            URL[] urls = { new File(componentPath).toURI().toURL() };

            // Load class
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Factory.class.getClassLoader());
            Class clazz = Class.forName(component, true, urlClassLoader);

            // Create a new instance
            Object instance = clazz.getMethod("getInstance").invoke(null);
            port = clazz.getDeclaredField("port").get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return port;
    }
}
