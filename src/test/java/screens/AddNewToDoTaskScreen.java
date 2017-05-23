package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.By;
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

    @AndroidFindBy(id = APP_PACKAGE_ID + "/toolbar")
    private MobileElement toolBar;

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
        return toolBar.findElement(By.className("android.widget.TextView"));
    }

    public TodoListScreen navigateToTodoListScreen() {
        taskSaveFavIcon.click();
        return new TodoListScreen(driver);
    }
}
