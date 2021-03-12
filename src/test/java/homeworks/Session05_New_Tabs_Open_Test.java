package homeworks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import parallel.TestBase;

import java.util.Set;

public class Session05_New_Tabs_Open_Test extends TestBase {

    @BeforeEach
    public void startBrowser() {
        loginToAdminPane(driver);
    }

    @Test
    void openCloseTabsTest() {

        driver.get(getBASE_URL() + "/admin/?app=countries&doc=edit_country");

        for (WebElement linkArrow : driver.findElements(By.cssSelector(".fa.fa-external-link"))) {

            String originalW = driver.getWindowHandle();
            Set<String> existWs = driver.getWindowHandles();
            linkArrow.click();
            String newW = wait.until(anyWindowOtherThan(existWs));
            driver.switchTo().window(newW);

            assertThat(driver.getTitle(), containsString("Wikipedia"));

            driver.close();
            driver.switchTo().window(originalW);

            assertThat(driver.getTitle(), containsString("Add New Country"));

        }

    }


    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
            return input -> {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            };
        }
    }

