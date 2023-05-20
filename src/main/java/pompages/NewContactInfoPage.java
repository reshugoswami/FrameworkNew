package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactInfoPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	
	public NewContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
}
