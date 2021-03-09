package sessions;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HarRequest;
import com.browserup.harreader.model.HarResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parallel.TestSettings;

public class ProxyTest extends TestSettings {
    WebDriver driver;
    WebDriverWait wait;
    BrowserUpProxy proxy;

    @BeforeEach
    public void start() {

        proxy = new BrowserUpProxyServer();
        proxy.start(0);

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        ChromeOptions opt = new ChromeOptions();
        opt.setCapability(CapabilityType.PROXY, seleniumProxy);

        driver = new ChromeDriver(opt);

        wait = new WebDriverWait(driver, 5);

        proxy.enableHarCaptureTypes( CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    }

    @Test
    public void proxyTest() {

        proxy.newHar("litecart");

        By sidebar = By.id("box-apps-menu");

        driver.navigate().to(getBASE_URL() + "/admin/");
        driver.findElement(By.name("username")).sendKeys(getLOGIN_NAME());
        driver.findElement(By.name("password")).sendKeys(getLOGIN_PASS());
        driver.findElement(By.cssSelector("button[name=login]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sidebar));

        Har har = proxy.getHar();

        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println("My_Log: " + request.getUrl() + " : " + request.getMethod() + " : " +
                    response.getStatus() + " : " + response.getContent().getText() + " : " +
                    entry.getTime() + "ms");
        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        proxy.stop();
    }

}