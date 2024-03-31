package masudeokarwork.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import masudeokar.TestComponents.BaseTest;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.CartPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.CheckOutPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.ConfiramationPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.LandingPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.ProductCatalog;


public class StepDefinationImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfiramationPage confiramationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage =launchApplication();
	}
	@Given ("^Logged in with username(.+) and password (.+)$")
	public void  logged_in_userName_and_password(String username, String password)
	{
		 productCatalog = landingPage.loginApplication(username,password);
	}
	@When("^I add product (.+) from cart$")
	public void i_add_product_from_cart(String productName)
	{
		@SuppressWarnings("unused")
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);		
	}
	@When("^CheckOut (.+) and submit the order$")
	public void CheckOut_and_submit_the_order(String productName) throws InterruptedException 
	{
		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(3000);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		confiramationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMessage = confiramationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void messageIsDisplayed(String arg1) throws Throwable {
		Assert.assertEquals(arg1, landingPage.getErrorMessage());
		System.out.println(landingPage.getErrorMessage());
		driver.quit();
	}
}
