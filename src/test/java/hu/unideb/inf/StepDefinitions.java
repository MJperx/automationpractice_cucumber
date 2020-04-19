package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    static WebDriver driver;

    static final String EMAIL = "v4l4mi_0419144030@valami.com";
    static final String PASSWD = "v4l4mi";

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("The home page is opened")
    public void theHomePageIsOpened() {
        driver.get("http://automationpractice.com/");
    }

    @And("The Sign in link is clicked")
    public void theSignInLinkIsClicked() {
        driver.findElement(By.className("login")).click();
    }

    @And("The Create an account button is clicked with email filled")
    public void theCreateAnAccountButtonIsClickedWithEmailFilled() {
        driver.findElement(By.id("email_create")).sendKeys(EMAIL);
        driver.findElement(By.id("SubmitCreate")).click();
    }

    @Then("Fill the required parameters")
    public void fillTheRequiredParameters() {
        driver.findElement(By.id("customer_firstname")).sendKeys("First");
        driver.findElement(By.id("customer_lastname")).sendKeys("Last");
        driver.findElement(By.id("passwd")).sendKeys(PASSWD);
        driver.findElement(By.id("firstname")).sendKeys("First");
        driver.findElement(By.id("lastname")).sendKeys("Last");
        driver.findElement(By.id("address1")).sendKeys("Address");
        driver.findElement(By.id("city")).sendKeys("City");
        driver.findElement(By.id("postcode")).sendKeys("23542");
        driver.findElement(By.id("phone_mobile")).sendKeys("301234567");

        Select drpState = new Select(driver.findElement(By.id("id_state")));
        drpState.selectByVisibleText("Alabama");
    }

    @Then("Click the Register button")
    public void clickTheRegisterButton() {
        driver.findElement(By.id("submitAccount")).click();
    }

    @Then("The Sign out button is clicked")
    public void theSignOutButtonIsClicked() {
        driver.findElement(By.className("logout")).click();
    }

    @Given("Check the user is logged in")
    public void checkTheUserIsLoggedIn() {
        String loggedInUser = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"))
                .getAttribute("innerHTML");
        Assert.assertNotEquals(0, loggedInUser);
        Assert.assertEquals("First Last", loggedInUser);
    }

    @Given("The Sign In button is clicked")
    public void theSignInButtonIsClicked() {
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Then("An email address required error message is shown")
    public void anEmailAddressRequiredErrorMessageIsShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("An email address required.", elements.get(0).getText());
    }

    @Given("The login form filled with wrong password")
    public void theLoginFormFilledWithWrongPassword() {
        driver.findElement(By.id("email")).sendKeys(EMAIL);
        driver.findElement(By.id("passwd")).sendKeys("notmypassword");
    }

    @Then("An Authentication failed message is shown")
    public void anAuthenticationFailedMessageIsShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        Assert.assertNotEquals(0, elements.size());
        Assert.assertEquals("Authentication failed.", elements.get(0).getText());
    }

    @Given("The login form filled with good paramaters")
    public void theLoginFormFilledWithGoodParamaters() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("passwd")).clear();

        driver.findElement(By.id("email")).sendKeys(EMAIL);
        driver.findElement(By.id("passwd")).sendKeys(PASSWD);
    }

    @Given("Fill the search input with something")
    public void fillTheSearchInputWithSomething() {
        driver.findElement(By.id("search_query_top")).sendKeys("Faded Short Sleeve T-shirts");
    }

    @Then("The Search icon is clicked")
    public void theSearchIconIsClicked() {
        driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
    }

    @And("Add the first element to cart")
    public void addTheFirstElementToCart() {
        List<WebElement> searchedItems = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
        Assert.assertEquals("Faded Short Sleeve T-shirts", searchedItems.get(0).findElement(By.className("product-name")).getAttribute("title"));

        Actions action = new Actions(driver);
        WebElement webElement = driver.findElement(By.className("first-item-of-tablet-line"));
        action.moveToElement(webElement).build().perform();
        driver.findElement(By.className("ajax_add_to_cart_button")).click();
        driver.findElement(By.className("continue")).click();
        Assert.assertEquals("Cart 1 Product", driver.findElement(By.className("shopping_cart")).getText());
    }

    @Given("Close browser")
    public void closeBrowser() {
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
