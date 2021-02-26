package parallel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SlowBase {

    static WebDriver driver;

    @BeforeAll
    public static void setup()
    {
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void  cleanup()
    {
        driver.quit();
    }
}

