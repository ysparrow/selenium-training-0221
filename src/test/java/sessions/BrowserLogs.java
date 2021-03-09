package sessions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import parallel.TestSettings;

import java.util.logging.Level;

public class BrowserLogs extends TestSettings {

    WebDriver drv;

    @BeforeEach
    public void newDrv() {
        ChromeOptions opt = new ChromeOptions();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        opt.setExperimentalOption("w3c", false);

        drv = new ChromeDriver(opt);
    }


    @Test
    public void simpleLogs() {

        System.out.println(drv.manage().logs().getAvailableLogTypes());

        drv.get(getTestURL());

        String[] logTypes = {"browser", "driver", "client"};

        for (String logType : logTypes) {
            for (LogEntry l : drv.manage().logs().get(logType).getAll()) {
                System.out.println(logType + ":" + l);
            }
        }
    }


    @Test
    public void performanceLogs() {

        System.out.println(drv.manage().logs().getAvailableLogTypes());
        drv.get(getBASE_URL());

        for (LogEntry entry : drv.manage().logs().get(LogType.PERFORMANCE)) {
            System.out.println(entry.toString());
        }
    }

    @AfterEach
    public void stopLocalDriver() {
        if (drv != null) {
            drv.quit();
            drv = null;
        }
    }

}
