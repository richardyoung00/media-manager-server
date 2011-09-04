package MediaManager.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyInjector {
    private int maxParentLevel = 5;

    public void injectProperties(Object o) {
        injectProperties(o, null);
    }

    private void injectProperties(Object o, Class cls) {
        if (cls == null) {
            cls = o.getClass();
        }
        Field[] fields = cls.getDeclaredFields();

        for (Field f : fields) {
            if (f.isAnnotationPresent(FromPropertiesFile.class)) {
                try {
                    String keyName;
                    String value = "";

                    keyName = f.getAnnotation(FromPropertiesFile.class).key();
                    if (keyName.isEmpty()) {
                        keyName = f.getName();
                    }
                    f.setAccessible(true);

                    try {
                        value = getProperty(keyName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (f.getType().getName().toLowerCase().equals("boolean")) {
                        f.setBoolean(o, Boolean.parseBoolean(value));
                    }
                    if (f.getType().getName().toLowerCase().equals("string")) {
                        f.set(o, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        maxParentLevel--;
        if (maxParentLevel > 0 && cls.getSuperclass() != null) {
            injectProperties(o, cls.getSuperclass());
        }

    }

    private String getProperty(String name) throws IOException {
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/MediaManager/app.properties");

        if (in == null) {
            throw new FileNotFoundException("property file  not found in the classpath");
        }

        prop.load(in);
        in.close();
        return prop.getProperty(name);
    }
}
