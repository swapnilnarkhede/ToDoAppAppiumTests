package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by swapnil on 16/05/17.
 */
public class TodoListScreen extends AbstractScreen {

    @AndroidFindBy(id = APP_PACKAGE_ID + "/toolbar")
    private  MobileElement toolbar;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/filteringLabel")
    private MobileElement todoFilterTitle;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/title")
    private MobileElement todoTaskTitle;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/fab_add_task")
    private MobileElement addNewTaskFabIcon;

    @AndroidFindBys(@AndroidFindBy(id = APP_PACKAGE_ID + "/title"))
    private List<MobileElement> taskListItems;

//    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]")
//    private  MobileElement contextActionMenu;

    @AndroidFindBy(id = APP_PACKAGE_ID + "/menu_filter")
    private MobileElement taskFilterMenu;

    @AndroidFindBy(className = "android.widget.ListView")
    private MobileElement menuItemList;

    @AndroidFindBys(@AndroidFindBy(id = APP_PACKAGE_ID + "/complete"))
    private List<MobileElement> taskCheckButton;

    public TodoListScreen(AppiumDriver driver) {
        super(driver);
    }

    public AddNewToDoTaskScreen addNewToDoTask() {
        addNewTaskFabIcon.click();
        return new AddNewToDoTaskScreen(driver);
    }

    public MobileElement getTodoFilterTitle() {
        return todoFilterTitle;
    }

    public MobileElement getTodoTaskTitle() {
        return todoTaskTitle;
    }

    public MobileElement getAddNewTaskFabIcon() {
        return addNewTaskFabIcon;
    }

    public MobileElement getNavbarTitle() {
        return toolbar.findElement(By.className("android.widget.TextView"));
    }

    public List<MobileElement> getTaskListItems() {
        return taskListItems;
    }

    public MobileElement getContextActionMenu() {
        return toolbar.findElement(By.className("android.widget.ImageView"));
    }

    public MobileElement getMenuItemList() {
        return menuItemList;
    }

    public MobileElement getTaskFilterMenu() {
        return taskFilterMenu;
    }

    public List<MobileElement> getTaskCheckButton() {
        return taskCheckButton;
    }

    public void markTaskAsCompleted(int taskListIndex) {
        final MobileElement selectedTaskCheckbox = taskCheckButton.get(taskListIndex - 1);
        selectedTaskCheckbox.click();
    }

    public void displayFilterTaskByStatus(final String taskStatus) {
        taskFilterMenu.click();
        final MobileElement menuItem = menuItemList.findElements(By.className("android.widget.TextView")).stream().filter(element -> element.getText().equals(taskStatus)).collect(Collectors.<MobileElement>toList()).get(0);
        menuItem.click();
//        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
    }
    public String getLatestTaskAddedTitle() {
        return taskListItems.get(taskListItems.size() - 1).getText();
    }

}
