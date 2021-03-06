package sessions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WaitTests {

    WebDriver drv;

    @BeforeEach
    void setUp() {

        drv = new ChromeDriver();
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown() {
        drv.quit();
    }

    @Test
    void findElement() {
        long startTime = System.currentTimeMillis();

        System.out.println("Started at: " + startTime);

        drv.get("https://google.com");

        if (!isElementPresent(drv, By.cssSelector("li.a5ccount.dropdown")))
            System.out.println("Element Present");
        else
            System.out.println("Element Absent");

        long finishTime = System.currentTimeMillis();

        System.out.println("Finished at: " + finishTime);
        System.out.println("Time spent: " + (finishTime - startTime)/1000f +"c." );
    }


    public boolean isElementPresent(WebDriver drv, By locator)
    {
        return drv.findElements(locator).size() > 0;
    }

}
