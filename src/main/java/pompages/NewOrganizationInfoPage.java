package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganizationInfoPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	
	public NewOrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
}
