import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import model.Device;
import model.Devices;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@RunWith(Parallelized.class)
public abstract class AbstractTest {

    protected AppiumDriver driver;

    @Parameter(value = 0)
    public String applicationName;
    @Parameter(value = 1)
    public String androidVersion;
    @Parameter(value = 2)
    public boolean isEmulator;

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
        if (isEmulator) {
            capabilities.setCapability("avd", applicationName);
        }
        capabilities.setCapability("app", AbstractTest.class.getResource(properties.getProperty("appApk")).getPath());

        URL appiumURL = new URL("http://127.0.0.1:4444/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);

    }

    private static Object[][] readTestingDevicesInfo() throws ParseException, IOException {
        String fileName = System.getProperty("devices");
        ObjectMapper objectMapper = new ObjectMapper();

        Devices devices = objectMapper.readValue(new File(fileName), Devices.class);

        final Object[][] devicesInfo = new Object[devices.getDeviceList().size()][3];

        int count = 0 ;
        for(Device device: devices.getDeviceList()) {
            devicesInfo[count][0] = device.getApplicationName();
            devicesInfo[count][1] = device.getAndroidVersion();
            devicesInfo[count][2] = device.isEmulator();
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
