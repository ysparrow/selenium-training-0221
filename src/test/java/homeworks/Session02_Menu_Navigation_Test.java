package homeworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import parallel.TestBase;

public class Session02_Menu_Navigation_Test extends TestBase {

    @BeforeEach
    public void startBrowser() {
        loginToAdminPane(driver);
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

}
