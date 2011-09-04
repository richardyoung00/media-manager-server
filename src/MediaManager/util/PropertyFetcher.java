package MediaManager.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFetcher {

    private static String fileUrl = "MediaManager/src/MediaManager/app.properties";
    private Properties prop;

    public String getString(String key) {
        try {

            return getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    private String getProperty(String name) throws IOException {
        if (prop == null) {
            prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/MediaManager/app.properties");

            prop.load(in);
            in.close();
        }
        return prop.getProperty(name);
    }
}
