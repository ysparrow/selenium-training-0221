package sessions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.TestSettings;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class RemoteRunTest extends TestSettings {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeEach
    void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "opera");
        capabilities.setCapability("browserVersion", "74.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);

        wait = new WebDriverWait(driver, 10);
        loginToAdminPane(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    void menuNavigationTest() {
        By menuItemsLocator = By.className("app");
        By subMenuItemsLocator = By.className("doc");
        By panelHeadingLocator = By.className("panel-heading");

        for (int menuIterator = 1 ; menuIterator <= driver.findElements(menuItemsLocator).size(); menuIterator++)
        {
            driver.findElement(By.xpath("//li[contains(@class,'app')]["+menuIterator+"]")).click();
            Assertions.assertTrue(isElementPresent(driver,panelHeadingLocator), "Heading not found");

            for (int submenuIterator = 1 ; submenuIterator <= driver.findElements(subMenuItemsLocator).size(); submenuIterator++)
            {
                driver.findElement(By.xpath("//li[contains(@class,'doc')]["+submenuIterator+"]")).click();
                Assertions.assertTrue(isElementPresent(driver, panelHeadingLocator), "Heading not found");
            }
        }
    }

}
