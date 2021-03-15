package sessions.multilayer.application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sessions.multilayer.data.Customer;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LiteCartApplication {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public String BASE_URL = "http://158.101.173.161";
    public String LOGIN_NAME = "testadmin";
    public String LOGIN_PASS = "R8MRDAYT_test";

    public LiteCartApplication() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    public void quit() {
        driver.quit();
    }

    private void logout() {
        driver.get(BASE_URL +"/logout");
    }

    public Set<String> getCustomerIds() {
        driver.get(BASE_URL + "/admin/?app=customers&doc=customers");
        return driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }

    public void loginToAdminPane() {
        driver.get(BASE_URL + "/admin");
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys(LOGIN_NAME);
            driver.findElement(By.name("password")).sendKeys(LOGIN_PASS);
            driver.findElement(By.name("password")).submit();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }
    }

    public void registerNewCustomer(Customer customer) {
        driver.get(BASE_URL + "/en/create_account");

        if (isElementPresent(By.name("decline_cookies"))) {
            driver.findElement(By.name("decline_cookies")).click();
        }

        driver.findElement(By.name("firstname")).sendKeys(customer.getFirstName());
        driver.findElement(By.name("lastname")).sendKeys(customer.getLastName());
        driver.findElement(By.name("address1")).sendKeys(customer.getAddress());
        driver.findElement(By.name("postcode")).sendKeys(customer.getPostcode());
        driver.findElement(By.name("city")).sendKeys(customer.getCity());
        new Select(driver.findElement(By.cssSelector("select[name=country_code]"))).selectByValue(customer.getCountry());
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=" + customer.getZone() + "]")));
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue(customer.getZone());
        driver.findElement(By.cssSelector("#box-create-account input[name=email]")).sendKeys(customer.getEmail());
        driver.findElement(By.name("phone")).sendKeys(customer.getPhone());
        driver.findElement(By.cssSelector("#box-create-account input[name=password]")).sendKeys(customer.getPassword());
        driver.findElement(By.cssSelector("#box-create-account input[name=confirmed_password]")).sendKeys(customer.getPassword());
        driver.findElement(By.name("terms_agreed")).click();
        driver.findElement(By.name("create_account")).click();

        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success"))).getText(),
                containsString("Your customer account has been created."));
        logout();
    }

    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }
}
