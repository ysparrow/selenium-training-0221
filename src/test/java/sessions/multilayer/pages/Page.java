package sessions.multilayer.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import sessions.multilayer.application.ApplicationContext;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    ApplicationContext appContext;

    public Page(ApplicationContext appContext) {

        this.appContext = appContext;
        this.driver = appContext.getDriver();
        this.wait = new WebDriverWait(driver, 5);

    }
}
