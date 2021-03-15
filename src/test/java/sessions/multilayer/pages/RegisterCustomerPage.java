package sessions.multilayer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import sessions.multilayer.application.ApplicationContext;
import sessions.multilayer.data.Customer;

public class RegisterCustomerPage extends Page {

    @FindBy(name = "firstname")
    private WebElement firstnameInput;

    @FindBy(name = "lastname")
    private WebElement lastnameInput;

    @FindBy(name = "address1")
    private WebElement address1Input;

    @FindBy(name = "postcode")
    private WebElement postcodeInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(css = "#box-create-account input[name=email]")
    private WebElement emailInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(css = "#box-create-account input[name=password]")
    private WebElement passwordInput;

    @FindBy(css = "#box-create-account input[name=confirmed_password]")
    private WebElement confirmedPasswordInput;

    @FindBy(name = "terms_agreed")
    private WebElement agreedTermsBox;

    @FindBy(name = "create_account")
    private WebElement createAccountButton;

    private final By declineCookies = By.name("decline_cookies");


    public RegisterCustomerPage(ApplicationContext appContext) {
        super(appContext);
        PageFactory.initElements(driver, this);
    }

    public RegisterCustomerPage open() {
        driver.get(appContext.getBaseUrl() + "/en/create_account");

        if (isElementPresent(declineCookies)) {
            driver.findElement(declineCookies).click();
        }

        return this;
    }

    public RegisterCustomerPage logout() {
        driver.get(appContext.getBaseUrl() + "/logout");
        return this;
    }

    public RegisterCustomerPage registerNewCustomer(Customer customer) {
        firstnameInput.sendKeys(customer.getFirstName());
        lastnameInput.sendKeys(customer.getLastName());
        address1Input.sendKeys(customer.getAddress());
        postcodeInput.sendKeys(customer.getPostcode());
        cityInput.sendKeys(customer.getCity());
        selectCountry(customer.getCountry());
        selectZone(customer.getZone());
        emailInput.sendKeys(customer.getEmail());
        phoneInput.sendKeys(customer.getPhone());
        passwordInput.sendKeys(customer.getPassword());
        confirmedPasswordInput.sendKeys(customer.getPassword());
        agreedTermsBox.click();
        createAccountButton.click();
        return this;
    }

    private void selectCountry(String country) {
        new Select(driver.findElement(By.cssSelector("select[name=country_code]"))).selectByValue(country);
    }

    private void selectZone(String zone) {
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("select[name=zone_code] option[value=" + zone + "]")));
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue(zone);
    }

    public boolean isCustomerLoggedOut() {
        return wait.until(ExpectedConditions.textToBe(By.cssSelector("li.account.dropdown > a"), "Sign In"));
    }

    public boolean isCustomerRegisteredMessageShown() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".alert.alert-success"), "Your customer account has been created."));
    }
}
