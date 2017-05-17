import org.junit.*;

import screens.AddNewToDoTaskScreen;
import screens.TodoListScreen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class NewToDoScreenTest extends AbstractTest{

   @Test
   public void itShouldDisplayAddNewToDoPageWhenAddTaskIsClicked() {
       TodoListScreen screen = new TodoListScreen(driver);
       final AddNewToDoTaskScreen addNewToDoTaskScreen = screen.addNewToDoTask();

       assertEquals("New TO-DO",addNewToDoTaskScreen.getNavBarTitle().getText());
       assertEquals("Title",addNewToDoTaskScreen.getTaskTitle().getText());
       assertEquals("Enter your TO-DO here.",addNewToDoTaskScreen.getTaskDescription().getText());
       assertNotNull(addNewToDoTaskScreen.getTaskSaveFavIcon());
   }
}
