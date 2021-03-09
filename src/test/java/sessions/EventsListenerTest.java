package sessions;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.TestSettings;
import sessions.listeners.Listener;


import java.util.List;

public class EventsListenerTest extends TestSettings {

    EventFiringWebDriver edr;
    WebDriverWait wait;

    @BeforeEach
    public void start() {
        edr = new EventFiringWebDriver(new ChromeDriver());
        edr.register(new Listener());
        wait = new WebDriverWait(edr, 5);
    }

    @AfterEach
    public void stop() {
        edr.quit();
    }

    @Test
    public void listenerTest() throws InterruptedException {

        performLogin();

        List<WebElement> menuItems = getMenuItems();
        int menuItemsCount = menuItems.size();

        for (int i = 0; i < menuItemsCount; i++) {
            menuItems.get(i).click();
            Assertions.assertTrue(isTagPresent());

            List<WebElement> subMenuItems = getSubMenuItems();
            int subMenuItemsCount = subMenuItems.size();

            if (subMenuItemsCount > 0) {
                for (int j = 0; j < subMenuItemsCount; j++) {
                    subMenuItems.get(j).click();
                    Assertions.assertTrue(isTagPresent());
                    subMenuItems = getSubMenuItems();
                }
            }
            menuItems = getMenuItems();
        }
    }

    private boolean isTagPresent() {
        return edr.findElements((By.cssSelector("div.panel-heading"))).size() > 0;
    }

    private List<WebElement> getMenuItems() {
        return edr.findElements(By.cssSelector("li.app"));
    }

    private List<WebElement> getSubMenuItems() {
        return edr.findElements(By.cssSelector("li.doc"));
    }

    private void performLogin() {
        By sidebar = By.id("box-apps-menu");

        edr.navigate().to(getBASE_URL() +"/admin/");

        if (isElementPresent(sidebar)) return;
        edr.findElement(By.name("username")).sendKeys(getLOGIN_NAME());
        edr.findElement(By.name("password")).sendKeys(getLOGIN_PASS());
        edr.findElement(By.cssSelector("button[name=login]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));
    }

    private boolean isElementPresent(By element) {
        return edr.findElements(element).size() > 0;
    }
}
