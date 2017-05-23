import org.junit.Test;
import screens.AddNewToDoTaskScreen;
import screens.TodoListScreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by swapnil on 17/05/17.
 */
public class ToDoListScreenTest extends AbstractTest {

    @Test
    public void itShouldSaveNewTaskAndDisplayNewTaskList() {
        TodoListScreen screen = new TodoListScreen(driver);
        final AddNewToDoTaskScreen addNewToDoTaskScreen = screen.addNewToDoTask();

        addNewToDoTaskScreen.getTaskTitle().sendKeys("Test with Appium");
        addNewToDoTaskScreen.getTaskDescription().sendKeys("Learn automation testing");
        final TodoListScreen todoListScreen = addNewToDoTaskScreen.navigateToTodoListScreen();
        screen.displayFilterTaskByStatus("All");

        assertEquals("TO-DO-MVP", todoListScreen.getNavbarTitle().getText());
        assertEquals("All TO-DOs", todoListScreen.getTodoFilterTitle().getText());
        assertNotNull(todoListScreen.getTaskListItems());
        assertEquals("Test with Appium", todoListScreen.getLatestTaskAddedTitle());
    }

    @Test
    public void itShouldMoveTaskInCompletedTaskList() {
        // given task
        TodoListScreen screen = new TodoListScreen(driver);
        addNewTask("First task", "First Task Description");
        addNewTask("Second task", "Second Task Description");

        screen.markTaskAsCompleted(4);
        screen.displayFilterTaskByStatus("Completed");
        assertEquals("Completed TO-DOs", screen.getTodoFilterTitle().getText());
        assertNotNull(screen.getTaskListItems());
        assertEquals(1, screen.getTaskListItems().size());

    }

    private void addNewTask(String title, String description) {
        TodoListScreen screen = new TodoListScreen(driver);
        final AddNewToDoTaskScreen addNewToDoTaskScreen = screen.addNewToDoTask();

        addNewToDoTaskScreen.getTaskTitle().sendKeys(title);
        addNewToDoTaskScreen.getTaskDescription().sendKeys(description);
        addNewToDoTaskScreen.navigateToTodoListScreen();
    }


    //@Test
   /* public void itShouldPopupOptionMenuItemsTest() {
        TodoListScreen screen = new TodoListScreen(driver);

        screen.getContextActionMenu().click();


    }
*/
}
