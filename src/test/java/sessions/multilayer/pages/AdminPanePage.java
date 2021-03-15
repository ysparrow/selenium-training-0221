package sessions.multilayer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sessions.multilayer.application.ApplicationContext;

public class AdminPanePage extends Page {

    public AdminPanePage(ApplicationContext appContext) {
        super(appContext);
    }

    public AdminPanePage open()
    {
        driver.get(appContext.getBaseUrl() + "/admin");
        return this;
    }

    public AdminPanePage login() {
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys(appContext.getAppUser().getLoginName());
            driver.findElement(By.name("password")).sendKeys(appContext.getAppUser().getLoginPass());
            driver.findElement(By.name("password")).submit();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }
        return this;
    }
}
