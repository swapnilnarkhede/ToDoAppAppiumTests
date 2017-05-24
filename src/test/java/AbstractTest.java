import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by swapnil on 16/05/17.
 */
@RunWith(Parallelized.class)
public abstract class AbstractTest {
    protected AppiumDriver driver;

    @Parameterized.Parameter(value = 0)
    public String applicationName;
    @Parameterized.Parameter(value = 1)
    public String androidVersion;

    @Parameterized.Parameters
    public static Object[] getDeviceInfo() throws Exception {
        return new Object[][]{{"OnePlusThree", "7.1.1"},{"emulator-5554","5.1"},{"Nexus 5","6.0.1"}};
    }


    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //These are the capabilities we must provide to run our test on TestObject
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformVersion", androidVersion);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appActivity", "com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity");
        capabilities.setCapability("appPackage", "com.example.android.architecture.blueprints.todomvp");
        capabilities.setCapability("applicationName", applicationName);
        capabilities.setCapability("app", AbstractTest.class.getResource("/todo-app.apk").getPath());

        URL appiumURL = new URL("http://127.0.0.1:4444/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);

    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
