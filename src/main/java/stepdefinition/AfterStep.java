package stepdefinition;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class AfterStep {
    public static WebDriver driver;
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
