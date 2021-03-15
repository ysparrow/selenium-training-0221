package sessions.multilayer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import sessions.multilayer.application.ApplicationContext;
import sessions.multilayer.data.Customer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class RegisterCustomerPage extends Page{

    public RegisterCustomerPage(ApplicationContext appContext) {
        super(appContext);
    }


    public RegisterCustomerPage open()
    {
        driver.get(appContext.getBaseUrl() + "/en/create_account");
        return this;
    }

    public RegisterCustomerPage logout() {
        driver.get(appContext.getBaseUrl() +"/logout");
        return this;
    }


    public RegisterCustomerPage registerNewCustomer(Customer customer) {

        if (isElementPresent(By.name("decline_cookies"))) {
            driver.findElement(By.name("decline_cookies")).click();
        }

        driver.findElement(By.name("firstname")).sendKeys(customer.getFirstName());
        driver.findElement(By.name("lastname")).sendKeys(customer.getLastName());
        driver.findElement(By.name("address1")).sendKeys(customer.getAddress());
        driver.findElement(By.name("postcode")).sendKeys(customer.getPostcode());
        driver.findElement(By.name("city")).sendKeys(customer.getCity());
        new Select(driver.findElement(By.cssSelector("select[name=country_code]"))).selectByValue(customer.getCountry());
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=" + customer.getZone() + "]")));
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue(customer.getZone());
        driver.findElement(By.cssSelector("#box-create-account input[name=email]")).sendKeys(customer.getEmail());
        driver.findElement(By.name("phone")).sendKeys(customer.getPhone());
        driver.findElement(By.cssSelector("#box-create-account input[name=password]")).sendKeys(customer.getPassword());
        driver.findElement(By.cssSelector("#box-create-account input[name=confirmed_password]")).sendKeys(customer.getPassword());
        driver.findElement(By.name("terms_agreed")).click();
        driver.findElement(By.name("create_account")).click();

        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success"))).getText(),
                containsString("Your customer account has been created."));
        return this;
    }



}
