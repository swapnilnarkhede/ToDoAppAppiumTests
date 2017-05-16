import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ToDoAppTest {
    public AppiumDriver driver;
    WebElement fabButton;
    final String APP_PACKAGE_ID = "com.example.android.architecture.blueprints.todomvp:id";

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //These are the capabilities we must provide to run our test on TestObject /*
        capabilities.setCapability("deviceName", "AndroidAppiumTest");
        capabilities.setCapability("platformVersion","5.1");
        capabilities.setCapability("platformName","Android");


        /* Check your Basic Setup page to find the URL that corresponds to your device */
        URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(appiumURL, capabilities);

    }

    /* In this test, we simply press the button "1"*/
   @Test
   public void itShouldDisplayAddNewToDoPageWhenAddTaskIsClicked() {
       // given
       fabButton = driver.findElementById(APP_PACKAGE_ID+"/fab_add_task");

       // when
       fabButton.click();

       //Then
       String navBarTitle = driver.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.TextView[1]").getText();
       String taskTitle = driver.findElementById(APP_PACKAGE_ID+"/add_task_title").getText();
       String taskDescription = driver.findElementById(APP_PACKAGE_ID+"/add_task_description").getText();
       WebElement taskSaveFavIcon = driver.findElementById(APP_PACKAGE_ID+"/fab_edit_task_done");

       assertEquals("New TO-DO",navBarTitle);
       assertEquals("Title",taskTitle);
       assertEquals("Enter your TO-DO here.",taskDescription);
       assertNotNull(taskSaveFavIcon);
   }

   @Test
   public void itShouldSaveNewTaskAndDisplayNewTaskList() {
       // given
       fabButton = driver.findElementById(APP_PACKAGE_ID + "/fab_add_task");
       fabButton.click();

       WebElement taskTitle = driver.findElementById(APP_PACKAGE_ID + "/add_task_title");
       WebElement taskDescription = driver.findElementById(APP_PACKAGE_ID + "/add_task_description");
       WebElement taskSave = driver.findElementById(APP_PACKAGE_ID + "/fab_edit_task_done");

       //when
       taskTitle.sendKeys("Test with Appium");
       taskDescription.sendKeys("Learn automation testing");
       taskSave.click();

       // then
       String navBarTitle = driver.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.TextView[1]").getText();
       final String listTitle = driver.findElementById(APP_PACKAGE_ID + "/filteringLabel").getText();
       final WebElement taskList = driver.findElementById(APP_PACKAGE_ID + "/tasks_list");
       final List taskListItems = driver.findElementsById(APP_PACKAGE_ID + "/title");

       assertEquals("TO-DO-MVP",navBarTitle);
       assertEquals("All TO-DOs",listTitle);
       assertNotNull(taskList);
       assertEquals(3,  taskListItems.size());
   }

   /* We disable the driver after EACH test has been executed. */
    @After
    public void teardown() {
        driver.quit();
    }
}
