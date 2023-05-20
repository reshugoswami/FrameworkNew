package pompages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLib.WebDriverUtility;

public class CreateContactPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	
	@FindBy(xpath = "//img[contains(@onclick,'Accounts&action')]")
	private WebElement orgPlusButton;
	
	@FindBy(xpath = "//form[@name='selectall']/descendant::tr[contains(@onmouseout,'lvtColData')]/td[1]/a")
	private List<WebElement> orgList;
	
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setLastName(String lastName) {
		lastNameTF.sendKeys(lastName);
	}
	
	public void selectExistingOrganization(WebDriverUtility web, String orgName) {
		orgPlusButton.click();
		String parentID = web.getParentWindowID();
		web.childBrowserPopUp();
		
		for(WebElement org: orgList) {
			if(org.getText().equals(orgName)) {
				org.click();
				break;
			}
		}
		web.switchToWindow(parentID);
	}
	
	public void clickSave() {
		saveButton.click();
	}
}
