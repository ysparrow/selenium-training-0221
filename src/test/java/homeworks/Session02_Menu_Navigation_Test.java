package homeworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import parallel.TestBase;

public class Session02_Menu_Navigation_Test extends TestBase {

    @BeforeEach
    public void startBrowser() {
        loginToAdminPane();
    }

    @Test
    void menuNavigationTest() {
        By menuItemsLocator = By.className("app");
        By subMenuItemsLocator = By.className("doc");
        By panelHeadingLocator = By.className("panel-heading");

        for (int menuIterator = 1 ; menuIterator <= driver.findElements(menuItemsLocator).size(); menuIterator++)
        {
            driver.findElement(By.xpath("//li[contains(@class,'app')]["+menuIterator+"]")).click();
            Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

            for (int submenuIterator = 1 ; submenuIterator <= driver.findElements(subMenuItemsLocator).size(); submenuIterator++)
            {
                driver.findElement(By.xpath("//li[contains(@class,'doc')]["+submenuIterator+"]")).click();
                Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");
            }
        }
    }

    void loginToAdminPane() {

        By username = By.cssSelector("input[name=username]");
        By password = By.cssSelector("input[name=password]");
        By loginBtn = By.cssSelector("button[name=login]");

        driver.get(getBASE_URL() + "/admin");

        if (isElementPresent(username)) {
            driver.findElement(username).sendKeys(getLOGIN_NAME());
            driver.findElement(password).sendKeys(getLOGIN_PASS());
            driver.findElement(loginBtn).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-apps-menu")));
        pause(3);
    }

    boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }

    void pause(int sec)
    {
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
