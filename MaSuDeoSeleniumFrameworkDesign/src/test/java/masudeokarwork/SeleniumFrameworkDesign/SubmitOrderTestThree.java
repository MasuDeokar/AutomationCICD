package masudeokarwork.SeleniumFrameworkDesign;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import masudeokar.TestComponents.BaseTest;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.CartPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.CheckOutPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.ConfiramationPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.OrderPage;
import pageObject.masudeokarwork.SeleniumFrameworkDesign.ProductCatalog;

public class SubmitOrderTestThree extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		@SuppressWarnings("unused")
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		ConfiramationPage confiramationPage = checkOutPage.submitOrder();
		String confirmMessage = confiramationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	// Below code is for order history  dependency.
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalog productCatalog = landingPage.loginApplication("mayureshdeokar@gmail.com", "Shamanth@123");
		OrderPage orderPage=productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	

	@DataProvider 
	public Object[][] getData() throws IOException {
		
		List<HashMap<String ,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//masudeokar//data//PurchaseOrder.json");
		return new  Object[][] {{data.get(0)},{data.get(1)}};
	}

}
