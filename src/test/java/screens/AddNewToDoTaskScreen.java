package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.support.FindBys;

/**
 * Created by swapnil on 16/05/17.
 */
public class AddNewToDoTaskScreen extends AbstractScreen {
    @AndroidFindBy(id = APP_PACKAGE_ID + "/add_task_title")
    private MobileElement taskTitle;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/add_task_description")
    private MobileElement taskDescription;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/fab_edit_task_done")
    private MobileElement taskSaveFavIcon;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.TextView[1]")
    private MobileElement navBarTitle;

    public AddNewToDoTaskScreen(AppiumDriver driver) {
        super(driver);
    }

    public MobileElement getTaskTitle() {
        return taskTitle;
    }

    public MobileElement getTaskDescription() {
        return taskDescription;
    }

    public MobileElement getTaskSaveFavIcon() {
        return taskSaveFavIcon;
    }

    public MobileElement getNavBarTitle() {
        return navBarTitle;
    }

    public TodoListScreen navigateToTodoListScreen() {
        taskSaveFavIcon.click();
        return new TodoListScreen(driver);
    }
}
