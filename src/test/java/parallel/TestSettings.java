package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TestSettings {

    String BASE_URL = "";
    String LOGIN_NAME = "";
    String LOGIN_PASS = "";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    public String getLOGIN_PASS() {
        return LOGIN_PASS;
    }

    public String getTestURL() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.html").getFile());
        return "file:///"+
                file.getAbsolutePath();
    }

    public void loginToAdminPane(WebDriver drv) {

        WebDriverWait wait = new WebDriverWait(drv, 10);

        By username = By.cssSelector("input[name=username]");
        By password = By.cssSelector("input[name=password]");
        By loginBtn = By.cssSelector("button[name=login]");

        drv.get(getBASE_URL() + "/admin");

        if (isElementPresent(drv, username)) {
            drv.findElement(username).sendKeys(getLOGIN_NAME());
            drv.findElement(password).sendKeys(getLOGIN_PASS());
            drv.findElement(loginBtn).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-apps-menu")));
       // pause(3);
    }

    public boolean isElementPresent(WebDriver driver, By element) {
        return driver.findElements(element).size() > 0;
    }

    public void pause(int sec)
    {
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
