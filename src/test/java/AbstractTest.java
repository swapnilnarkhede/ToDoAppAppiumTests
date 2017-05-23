import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by swapnil on 16/05/17.
 */
public abstract class AbstractTest {
    protected static AppiumDriver driver;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //These are the capabilities we must provide to run our test on TestObject /*
        capabilities.setCapability("deviceName", "AndroidAppiumTest");
        capabilities.setCapability("platformVersion","6.0.1");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appActivity","com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity");
        capabilities.setCapability("appPackage","com.example.android.architecture.blueprints.todomvp");
        capabilities.setCapability("avd","APPIUM_TEST_22");
        capabilities.setCapability("device","Android");
        final String path = AbstractTest.class.getResource("/todo-app.apk").getPath();
        capabilities.setCapability("app", path);


        /* Check your Basic Setup page to find the URL that corresponds to your device */
        URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);

    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
