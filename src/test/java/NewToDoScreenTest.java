import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Test;
import org.openqa.selenium.By;
import screens.AddNewToDoTaskScreen;
import screens.TodoListScreen;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class NewToDoScreenTest extends AbstractTest {
    @Test
    public void itShouldDisplayAddNewToDoPageWhenAddTaskIsClicked() throws MalformedURLException, InterruptedException {

        TodoListScreen screen = new TodoListScreen(driver);
        final AddNewToDoTaskScreen addNewToDoTaskScreen = screen.addNewToDoTask();

        assertEquals("New TO-DO", addNewToDoTaskScreen.getNavBarTitle().getText());
        assertEquals("Title", addNewToDoTaskScreen.getTaskTitle().getText());
        assertEquals("Enter your TO-DO here.", addNewToDoTaskScreen.getTaskDescription().getText());
        assertNotNull(addNewToDoTaskScreen.getTaskSaveFavIcon());
    }

//    @Test
//    public void itShouldGoInBackGroundAndLaunchAgainTest() throws InterruptedException {
//        TodoListScreen todoListScreen = new TodoListScreen(driver);
//        showHomeScreen();
//        openAppFromRecent();
//        Thread.sleep(6000);
//        assertEquals("TO-DO-MVP", todoListScreen.getNavbarTitle().getText());
//
//    }
//
//    private void openAppFromRecent() {
//        ((AndroidDriver)(driver)).pressKeyCode(187);
//        System.out.println("------------> " + ((AndroidDriver)(driver)).findElements(By.className("TextView")));
//    }
//
//    private void showHomeScreen() {
//        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
//
//    }
}
