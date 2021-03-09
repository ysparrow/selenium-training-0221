package sessions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import parallel.TestBase;


public class WindowsAndAlerts extends TestBase {

    @BeforeEach
    public void open() {
        driver.get(getTestURL());
    }

    @Test
    public void windowTest() {
        System.out.println("Pos: " + driver.manage().window().getPosition());
        driver.manage().window().setPosition(new Point(300, 100));
        System.out.println("Pos: " + driver.manage().window().getPosition());
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().setSize(new Dimension(300, 140));
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.getCurrentUrl();
    }

    @Test
    public void alertsTest() {
        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.sendKeys("Text to enter");
        alert.accept();

        driver.findElement((By.id("text"))).sendKeys("lalala");

        Assertions.assertEquals("lalala", driver.findElement(By.id("text")).getAttribute("value"));
    }

    @Test
    public void iFrameTest() {
        driver.get("http://jsbin.com/bicukojabe/edit?html,output");
        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe.stretch"))));
        driver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("JS Bin Output "))));
        driver.findElement(By.id("test")).sendKeys("Texts in field inside frame");
    }

    @Test
    void uploadPicture() {

        By sidebar = By.id("box-apps-menu");
        driver.navigate().to(getBASE_URL() +"/admin/");
        driver.findElement(By.name("username")).sendKeys(getLOGIN_NAME());
        driver.findElement(By.name("password")).sendKeys(getLOGIN_PASS());
        driver.findElement(By.cssSelector("button[name=login]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));

        driver.get(getBASE_URL()+"/admin/?category_id=0&app=catalog&doc=edit_product");

        driver.findElement(By.cssSelector("input[type=file]")).sendKeys("/home/yarik/Documents/BlackDuck.png");

        driver.findElement(By.cssSelector("#tab-general > div > div:nth-child(2) > div:nth-child(1) > div > input")).sendKeys("New Product");
    }
}