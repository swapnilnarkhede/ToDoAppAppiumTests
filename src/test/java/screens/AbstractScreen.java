package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by swapnil on 16/05/17.
 */
public class AbstractScreen {
    protected AppiumDriver driver;
    protected final String APP_PACKAGE_ID = "com.example.android.architecture.blueprints.todomvp:id";

    public AbstractScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
