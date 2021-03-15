package sessions.multilayer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import sessions.multilayer.data.Customer;
import java.util.Set;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerRegistrationTests extends CommonTest {

    @ParameterizedTest
    @MethodSource("customersProvider")
    public void canRegisterCustomer(Customer customer) {
        loginToAdminPane();
        Set<String> oldIds = getCustomerIds();
        registerNewCustomer (customer);
        Set<String> newIds = getCustomerIds();
        assertThat(oldIds, everyItem(is(in(newIds))));
        assertThat(newIds.size(), equalTo(oldIds.size() + 1));
    }

    private void logout() {
        driver.get(BASE_URL +"/logout");
        assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.account.dropdown > a")))
                        .getText()
                        .contains("Sign In"));
    }

    private Set<String> getCustomerIds() {
        driver.get(BASE_URL + "/admin/?app=customers&doc=customers");
        return driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }

    private void loginToAdminPane() {
        driver.get(BASE_URL + "/admin");
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys(LOGIN_NAME);
            driver.findElement(By.name("password")).sendKeys(LOGIN_PASS);
            driver.findElement(By.name("password")).submit();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }
    }

    private void registerNewCustomer(Customer customer) {
        driver.get(BASE_URL + "/en/create_account");

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
        logout();
    }

    static Stream<Customer> customersProvider() {
        return Stream.of(
                Customer.newEntity()
                        .withFirstName("test_Adam").withLastName("test_Smith").withPhone("+0123456789")
                        .withAddress("Hidden Place").withPostcode("12345").withCity("New City")
                        .withCountry("US").withZone("KS")
                        .withEmail("test_Adam" + System.currentTimeMillis() + "@smith.me")
                        .withPassword("qwerty").build(),
                Customer.newEntity()
                        .withFirstName("test_Chuck").withLastName("test_Norris").withPhone("+0123456789")
                        .withAddress("Everywhere").withPostcode("12345").withCity("Test City")
                        .withCountry("US").withZone("KS")
                        .withEmail("test_Chuck" + System.currentTimeMillis() + "@norris.is.watching.you")
                        .withPassword("*******").build()
        );
    }


    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }
}
