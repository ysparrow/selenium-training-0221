package homeworks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import parallel.TestBase;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Session04_Add_Remove_Items_Test extends TestBase {

    Random random = new Random();

    By itemsCountLabel = By.cssSelector(".badge.quantity");
    By popularProducts = By.cssSelector("#box-popular-products .product-column");
    By addToCartBtn = By.cssSelector("[name=add_cart_product]");
    By openCart = By.cssSelector("#cart");
    By deleteItemBtn = By.cssSelector("[name='remove_cart_item']");
    By noItemsTxt = By.xpath("//*[text()='There are no items in your cart.']");
    By cartItemsTable = By.cssSelector(".items.list-unstyled");
    String popularProductXPath = "//*[@id='box-popular-products']//*[@class='product-column'][%s]";


    @BeforeEach
    void setUp() {
        driver.get(getBASE_URL());
    }

    @Test
    void addRemoveItemsTest() {
        int itemsCount = 3;

        addProductsToTheCart(itemsCount);

        assertThat(driver.findElement(itemsCountLabel).getText(), is(String.valueOf(itemsCount)));

        cleanUpCart();

        assertThat(driver.findElement(itemsCountLabel).getText(), is(""));
    }

    private void addProductsToTheCart(Integer itemsCount) {

        for (int i = 1; i <= itemsCount; i++) {
            addRandomProduct();
            wait.until(elementToBeClickable(addToCartBtn)).click();
            wait.until(textToBePresentInElementLocated(itemsCountLabel, String.valueOf(i)));
            driver.get(getBASE_URL());
        }
    }

    private void addRandomProduct() {
        int items = driver.findElements(popularProducts).size();
        WebElement item = wait.until(elementToBeClickable(By.xpath(String.format(popularProductXPath, random.nextInt(items) + 1))));
        new Actions(driver).moveToElement(item).pause(500).click(item).perform();
    }

    private void cleanUpCart() {

        wait.until(elementToBeClickable(openCart)).click();

        while (!isElementPresent(noItemsTxt)) {
            WebElement table = wait.until(elementToBeClickable(cartItemsTable));
            wait.until(elementToBeClickable(deleteItemBtn)).click();
            wait.until(stalenessOf(table));
        }

        driver.get(getBASE_URL());
    }


}
