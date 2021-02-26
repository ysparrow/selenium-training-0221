import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SimpleTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions opts = new ChromeOptions();
        opts.setCapability("acceptInsecureCerts", true);
        opts.addArguments("start-fullscreen");
        opts.setExperimentalOption("w3c",false);

        driver = new ChromeDriver(opts);

        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void verifyGoogleSearchWorks() {
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.ENTER);
        Assertions.assertEquals("Selenium WebDriver", driver.findElement(By.cssSelector("h3")).getText(), "Text on site is differ than expected");
    }


}
