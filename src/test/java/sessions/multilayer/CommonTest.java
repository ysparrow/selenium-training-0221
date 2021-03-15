package sessions.multilayer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public String BASE_URL = "http://158.101.173.161";
    public String LOGIN_NAME = "testadmin";
    public String LOGIN_PASS = "R8MRDAYT_test";


    @BeforeAll
    public static void startDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @AfterAll
    public static void stopDriver() {
        driver.quit();
    }

}
