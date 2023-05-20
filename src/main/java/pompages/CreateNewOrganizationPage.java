package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLib.WebDriverUtility;

public class CreateNewOrganizationPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "accountname")
	private WebElement orgNameTF;
	
	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "accounttype")
	private WebElement typeDD;
	
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setOrgName(String orgName) {
		orgNameTF.sendKeys(orgName);
	}
	
	public void selectIndustry(WebDriverUtility web, String value) {
		web.dropdown(industryDD, value);
	}
	
	public void selectType(WebDriverUtility web, String value) {
		web.dropdown(typeDD, value);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
}
