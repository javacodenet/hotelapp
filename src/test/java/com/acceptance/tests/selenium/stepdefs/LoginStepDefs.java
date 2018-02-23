package com.acceptance.tests.selenium.stepdefs;

import com.acceptance.tests.selenium.SharedWebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginStepDefs {
    @Given("^User enters \"([^\"]*)\" as username and \"([^\"]*)\" as password$")
    public void userEntersAsUsernameAndAsPassword(String username, String password) throws Throwable {
        SharedWebDriver.getInstance().waitForAngular();
        SharedWebDriver.getInstance().getDriver().findElement(By.id("username")).sendKeys(username);
        SharedWebDriver.getInstance().getDriver().findElement(By.id("password")).sendKeys(password);
    }

    @When("^he clicks submit$")
    public void heClicksSubmit() throws Throwable {
       SharedWebDriver.getInstance().getDriver().findElement(By.id("login")).click();
       SharedWebDriver.getInstance().waitForAngular();
    }

    @Then("^login successful page should be displayed$")
    public void loginSuccessfulPageShouldBeDisplayed() throws Throwable {
        assertThat(SharedWebDriver.getInstance().getDriver().findElement(By.tagName("h2")).getText(), is("You have logged in!"));
    }
}
