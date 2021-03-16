package sessions;


import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTest {

    @BeforeEach
    public void start() {
        //Configuration.driverManagerEnabled = false;
        //Configuration.browser="firefox";
    }

    @Test
    public void searchAndOpenSelenideSite() {

        open("http://google.com");
        $(By.name("q")).sendKeys("selenide" + Keys.ENTER);
        $("h3").click();
        $("div.news div.news-line").shouldHave(text("Вышла Selenide 5.15.0"));

    }


}
