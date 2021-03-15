package sessions.multilayer.pages;

import org.openqa.selenium.By;
import sessions.multilayer.application.ApplicationContext;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AdminCustomersPage extends AdminPanePage{
    public AdminCustomersPage(ApplicationContext appContext) {
        super(appContext);
    }

    public AdminCustomersPage open()
    {
        driver.get(appContext.getBaseUrl() + "/admin");
        return this;
    }

    public Set<String> getCustomerIds() {
        driver.get(appContext.getBaseUrl() + "/admin/?app=customers&doc=customers");
        return driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }
}
