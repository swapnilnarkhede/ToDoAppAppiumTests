import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

@RunWith(Parallelized.class)
public abstract class AbstractTest {

    protected AppiumDriver driver;

    @Parameterized.Parameter(value = 0)
    public String applicationName;
    @Parameterized.Parameter(value = 1)
    public String androidVersion;

    @Parameterized.Parameters
    public static Object[] getDeviceInfo() throws Exception {
        return readTestingDevicesInfo();
    }

    @Before
    public void setup() throws MalformedURLException, InstantiationException {
        Properties properties = getProperties();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", properties.getProperty("deviceName"));
        capabilities.setCapability("platformVersion", androidVersion);
        capabilities.setCapability("platformName", properties.getProperty("deviceName"));
        capabilities.setCapability("appActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("appPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("applicationName", applicationName);
        capabilities.setCapability("app", AbstractTest.class.getResource(properties.getProperty("appApk")).getPath());

        URL appiumURL = new URL("http://127.0.0.1:4444/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);

    }

    private static Object[][] readTestingDevicesInfo() throws ParseException, IOException {
        String fileName = System.getProperty("devices");

        JSONParser parser = new JSONParser();

        final JSONObject devices = (JSONObject) parser.parse(new FileReader(fileName));

        final JSONArray deviceList = (JSONArray) devices.get("devices");

        final String[][] devicesInfo = new String[deviceList.size()][2];

        int count = 0 ;
        for(Object o: deviceList) {
            devicesInfo[count][0] = (((JSONObject) o).get("applicationName")).toString();
            devicesInfo[count][1] = (((JSONObject) o).get("androidVersion")).toString();
            count++;
        }

        return devicesInfo;

    }

    private Properties getProperties() throws InstantiationException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            String fileName = System.getProperty("configFile");

            if(null == fileName) {
                throw new FileNotFoundException("couldn't file configFile in system path, please specify configFile value in path");
            }
            inputStream = new FileInputStream(fileName);
            properties.load(inputStream);

            if (properties == null) {
                throw new InstantiationException("properties are missing");
            };

        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e){

                }

            }
        }
        return properties;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
