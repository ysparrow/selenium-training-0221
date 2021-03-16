package sessions.steps;


import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MyStepdefs {
    @Given("^Google search is opened in my browser$")
    public void googleSearchIsOpenInMyBrowser() throws Throwable {
        Configuration.browser = Browsers.CHROME;
        Configuration.driverManagerEnabled = false;
        open("http://www.google.com");
    }

    @When("^I enter search term \"([^\"]*)\"$")
    public void iEnterSearchTerm(String arg0) throws Throwable {
        $("[name=q]").sendKeys(arg0 + Keys.ENTER);
    }

    @Then("^Search results contains links for \"([^\"]*)\" related resources$")
    public void searchResultsContainsLinksForRelatedResources(String arg0) throws Throwable {
        $("h3").shouldHave(Condition.text(arg0));
    }

    @When("^I click on first search results$")
    public void iClickOnFirstSearchResults() throws Throwable {
       $("h3").click();
    }

    @Then("^Opened page should contain info about \"([^\"]*)\"$")
    public void openedPageShouldContainInfoAbout(String arg0) throws Throwable {
        $(byText(arg0)).should(Condition.exist);
    }

    @And("Search results page contains {int} results")
    public void searchResultsPageContainsResults(int arg0) {
        $$("h3").shouldHave(CollectionCondition.size(arg0));
    }

    @When("I click second result")
    public void iClickSecondResult() {

        $$("h3").get(1).click();

    }

    @Then("Something Happened")
    public void somethingHappened() {
        System.out.println("Something happened!");
    }

    @Then("I see GlobalLogic main page")
    public void iSeeGlobalLogicMainPage() {
       $("a.navbar-brand.logo").should(Condition.have(Condition.attribute("href","https://www.globallogic.com/ua/")));
    }

    @And("all elements are present")
    public void allElementsArePresent() {

    }


//    @And("Search results page contains {int} results")
//    public void searchResultsPageContainsResults(int arg0) {
//        $$("h3").shouldHaveSize(arg0);
//    }

}
