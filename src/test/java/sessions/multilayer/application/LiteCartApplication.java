package sessions.multilayer.application;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.qatools.properties.PropertyLoader;
import sessions.multilayer.Config;
import sessions.multilayer.data.Customer;
import sessions.multilayer.pages.AdminCustomersPage;
import sessions.multilayer.pages.AdminPanePage;
import sessions.multilayer.pages.RegisterCustomerPage;

import java.util.Set;

public class LiteCartApplication {

    private static WebDriver driver;

    private final AdminPanePage adminPanePage;
    private final AdminCustomersPage adminCustomersPage;
    private final RegisterCustomerPage registerCustomerPage;

    public LiteCartApplication() {

        Config config = PropertyLoader.newInstance().populate(Config.class);

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        ApplicationContext appContext = new ApplicationContext();
        appContext.setDriver(driver);
        appContext.setBaseUrl(config.getBaseUrl());
        appContext.setAppUser(new ApplicationContext.AppUser(config.getAdminUserName(), config.getAdminUserPass()));

        adminPanePage = new AdminPanePage(appContext);
        adminCustomersPage = new AdminCustomersPage(appContext);
        registerCustomerPage = new RegisterCustomerPage(appContext);
    }

    @Step
    public void loginToAdminPane() {
        adminPanePage.open().login();
    }

    @Step
    public Set<String> getCustomerIds() {
        return adminCustomersPage.open().getCustomerIds();
    }

    @Step
    public void registerNewCustomer(Customer customer) {
        registerCustomerPage.open().registerNewCustomer(customer);
    }

    @Step
    public boolean isCustomerLoggedOut() {
        return registerCustomerPage.isCustomerLoggedOut();
    }

    @Step
    public void customerLogout() {
        registerCustomerPage.logout();
    }

    @Step
    public void closeApp() {
        driver.quit();
    }

    @Step
    public boolean isCustomerRegisteredMessageShown() {
        return registerCustomerPage.isCustomerRegisteredMessageShown();
    }
}
