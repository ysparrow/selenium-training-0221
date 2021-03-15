package sessions.multilayer.application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sessions.multilayer.CustomerRegistrationTests;
import sessions.multilayer.data.Customer;
import sessions.multilayer.pages.AdminCustomersPage;
import sessions.multilayer.pages.AdminPanePage;
import sessions.multilayer.pages.RegisterCustomerPage;

import java.util.Set;

public class LiteCartApplication {

    private static WebDriver driver;

    private  final String BASE_URL = "http://158.101.173.161";
    private  final String LOGIN_NAME = "testadmin";
    private  final String LOGIN_PASS = "R8MRDAYT_test";

    private final AdminPanePage adminPanePage;
    private final AdminCustomersPage adminCustomersPage;
    private final RegisterCustomerPage registerCustomerPage;


    public LiteCartApplication() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        ApplicationContext appContext = new ApplicationContext();
        appContext.setDriver(driver);
        appContext.setBaseUrl(BASE_URL);
        appContext.setAppUser(new ApplicationContext.AppUser(LOGIN_NAME,LOGIN_PASS));

        adminPanePage = new AdminPanePage(appContext);
        adminCustomersPage = new AdminCustomersPage(appContext);
        registerCustomerPage = new RegisterCustomerPage(appContext);
    }

    public void loginToAdminPane() {
        adminPanePage.open().login();
    }

    public Set<String> getCustomerIds() {
        return adminCustomersPage.open().getCustomerIds();
    }

    public void registerNewCustomer(Customer customer) {
        registerCustomerPage.open().registerNewCustomer(customer).logout();
    }

    public void closeApp() {
        driver.quit();
    }
}
