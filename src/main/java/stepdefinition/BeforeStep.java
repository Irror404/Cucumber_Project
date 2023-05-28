package stepdefinition;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static utils.WebDriverRunner.getDriver;

public class BeforeStep {
    private WebDriver driver;

    @Given("Open the site {string}")
    public void iAmOnTheHomepage(String url) {
        System.setProperty("webdriver.chrome.driver", "/Users/irina/Downloads/chromedriver");
        driver = getDriver();
        driver.get("https://onliner.by");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
}
