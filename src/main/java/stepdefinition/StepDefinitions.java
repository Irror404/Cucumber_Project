package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static utils.WebDriverRunner.getDriver;

public class StepDefinitions {

    private WebDriver driver;

    @Given("Open the site {string}")
    public void iAmOnTheHomepage(String url) {
        System.setProperty("webdriver.chrome.driver", "/Users/irina/Downloads/chromedriver");
        driver = getDriver();
        driver.get("https://onliner.by");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @When("I search for a product {string}")
    public void iSearchForAProduct(String productName) {
        WebElement searchInput = driver.findElement(By.xpath("//input[@class='fast-search__input']"));
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }

    @And("I select the first search result")
    public void iSelectTheFirstSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        By searchResultsLocator = By.xpath("//ul[@class='search__results']/li[@class='search__result']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsLocator));
        List<WebElement> catalogList = driver.findElements(searchResultsLocator);
        if (!catalogList.isEmpty()) {
            // Выбор первого результата
            WebElement firstSearchResult = catalogList.get(0);
            firstSearchResult.click();
        } else {
            throw new RuntimeException("Результаты поиска отсутствуют");
        }
        /*WebElement firstSearchResult = driver.findElement(By.xpath("//ul[@class='search__results']/li[@class='search__result'][1]"));
        firstSearchResult.click();*/
    }

    @And("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(@class, 'button') and contains(@class, 'button_orange') and contains(@class, 'product__button')]"));
        addToCartButton.click();
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        WebElement cartLink = driver.findElement(By.xpath("//a[@class='button-style button-style_base-alter button-style_primary product-aside__button product-aside__button_narrow product-aside__button_cart button-style_expletive' and text()='В корзину']"));
        assertTrue(cartLink.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



