package sessions.multilayer;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomerRegistrationTests extends CommonTest {

    @Test
    public void canRegisterCustomer() {

        driver.get(BASE_URL + "/admin");

        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys(LOGIN_NAME);
            driver.findElement(By.name("password")).sendKeys(LOGIN_PASS);
            driver.findElement(By.name("password")).submit();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }

        driver.get(BASE_URL + "/admin/?app=customers&doc=customers");
        Set<String> oldIds = driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());


        String email = "testAuto" + System.currentTimeMillis() + "@smith.me";
        driver.get(BASE_URL + "/en/create_account");

        if (isElementPresent(By.name("decline_cookies"))) {
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


        driver.get(BASE_URL + "/admin/?app=customers&doc=customers");
        Set<String> newIds = driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());

        assertThat(oldIds, everyItem(is(in(newIds))));
        assertThat(newIds.size(), equalTo(oldIds.size() + 1));
    }

    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }
}
