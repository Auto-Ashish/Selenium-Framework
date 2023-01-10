package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import tests.base.BaseClass;

public class StepDefinitionsOne {
	
	BaseClass baseObj=null;
	
@Before
public void beforeHookOfCucumber() {
	baseObj=new BaseClass();
	baseObj.startExtentReporting();
	baseObj.createBrowserInstance();
}
	

@After
public void afterHookOfCucumber() {
	baseObj.closeBrowserInstance();
	baseObj.closeReporting();
}
	
	

@Given("User launched myntra webpage")
public void user_launched_myntra_webpage() {
   HomePage homePage=new HomePage(baseObj.driver);
   homePage.verifyObjectsOnThisPage();
}




@When("User enters username as {string} and password as {string}")
public void user_enters_username_as_and_password_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
   // throw new io.cucumber.java.PendingException();
}
@Then("User should be able to login sucessfully and new page open")
public void user_should_be_able_to_login_sucessfully_and_new_page_open() {
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.java.PendingException();
}
	

}
