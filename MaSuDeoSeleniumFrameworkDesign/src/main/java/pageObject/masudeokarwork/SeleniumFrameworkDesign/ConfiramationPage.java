package pageObject.masudeokarwork.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.masudeokarwork.AbstractComponents;

public class ConfiramationPage extends AbstractComponents {

	WebDriver driver;

	public ConfiramationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	@FindBy(css = ".toast-title")
	WebElement finalMessage;

	public String getConfirmationMessage() {
		System.out.println(finalMessage.getText());
		waitForWebElementToApear(finalMessage);
		return confirmationMessage.getText();

	}
}
