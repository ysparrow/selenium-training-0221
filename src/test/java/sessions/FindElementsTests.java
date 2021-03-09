package sessions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindElementsTests {

    WebDriver drv;

    @BeforeEach
    public void start() {
        drv = new ChromeDriver();
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    void findElement() throws InterruptedException {

        drv.get("https://google.com");

        if (isElementPresent(drv, By.cssSelector("li.account.dropdown"))) {
            System.out.println("Element is present");
        } else {
            System.out.println("Element is absent");
        }

    }


    @Test
    void findElementByDifferentLocators() {

        drv.get("https://www.google.com/");

        WebDriverWait wait = new WebDriverWait(drv, 10);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name=q]")));

        WebElement el1 = drv.findElement(By.cssSelector("[name=q]"));
        WebElement el2 = drv.findElement(By.name("q"));
        WebElement el3 = drv.findElement(By.xpath("//*[@name='q']"));

        System.out.println(Integer.toBinaryString(el1.hashCode()));
        System.out.println(Integer.toBinaryString(el2.hashCode()));
        System.out.println(Integer.toBinaryString(el3.hashCode()));

    }

    @Test
    public void jsExecutor() {
        drv.get("https://www.w3.org/");

        List<WebElement> links = (List<WebElement>) ((JavascriptExecutor) drv)
                .executeScript("return document.getElementsByClassName('headline')");

        for (WebElement item : links) {
            System.out.println(item.getTagName());
        }

        drv.get("http://www.google.com");

        List<WebElement> input = (List<WebElement>) ((JavascriptExecutor) drv).executeScript("return document.getElementsByName('q')");
        input.get(0).sendKeys("webdriver");

    }

    @AfterEach
    public void stop() {
        drv.quit();

    }


    public boolean isElementPresent(WebDriver drv, By locator) {

        return drv.findElements(locator).size() > 0;
//
//        try {
//            drv.findElement(locator);
//            return true;
//        }
//        catch(NoSuchElementException ex)
//        {
//            return false;
//        }

    }

}
