package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	//Declaration
	//@FindBy (xpath = "//a[@class='hdrLink']")
	//private WebElement pageHeader;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement plusButton;
	
	//Initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	//public String getPageHeader() {
		//return pageHeader.getText();
	
	public void clickPlusButton() {
		plusButton.click();
	}
}
