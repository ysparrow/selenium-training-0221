package sessions.multilayer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CustomerRegistrationTests {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void startDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @AfterAll
    public static void stopDriver() {
        driver.quit();
    }

    @Test
    public void canRegisterCustomer() {

        String email = "testAuto" + System.currentTimeMillis() + "@smith.me";
        driver.get("http://158.101.173.161/en/create_account");

        if (isElementPresent(By.name("decline_cookies")))
        {
            driver.findElement(By.name("decline_cookies")).click();
        }

        driver.findElement(By.name("firstname")).sendKeys("testAdam");
        driver.findElement(By.name("lastname")).sendKeys("testSmith");
        driver.findElement(By.name("address1")).sendKeys("testHidden Place");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("test New City");
        new Select(driver.findElement(By.cssSelector("select[name=country_code]"))).selectByValue("US");
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=KS]")));
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue("KS");
        driver.findElement(By.cssSelector("#box-create-account input[name=email]")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+0123456789");
        driver.findElement(By.cssSelector("#box-create-account input[name=password]")).sendKeys("qwerty");
        driver.findElement(By.cssSelector("#box-create-account input[name=confirmed_password]")).sendKeys("qwerty");
        driver.findElement(By.name("terms_agreed")).click();
        driver.findElement(By.name("create_account")).click();

        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success"))).getText(),
                containsString("Your customer account has been created."));

    }

    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() >0;
    }
}
