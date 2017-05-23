import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;
import screens.AddNewToDoTaskScreen;
import screens.TodoListScreen;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class NewToDoScreenTest extends AbstractTest{
    @Test
    public void itShouldDisplayAddNewToDoPageWhenAddTaskIsClicked() throws MalformedURLException {

        TodoListScreen screen = new TodoListScreen(driver);
        final AddNewToDoTaskScreen addNewToDoTaskScreen = screen.addNewToDoTask();

        assertEquals("New TO-DO", addNewToDoTaskScreen.getNavBarTitle().getText());
        assertEquals("Title", addNewToDoTaskScreen.getTaskTitle().getText());
        assertEquals("Enter your TO-DO here.", addNewToDoTaskScreen.getTaskDescription().getText());
        assertNotNull(addNewToDoTaskScreen.getTaskSaveFavIcon());
    }
}
