package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLib.TabNames;
import genericLib.WebDriverUtility;

public class HomePage {

	private String commonPath = "//table[@class='hdrTabBg']/descendant::a[text()='%s']";
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminIcon;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	@FindBy(id = "qccombo")
	private WebElement quickCreateDD;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickRequiredTab(TabNames tabname, WebDriverUtility webUtil) {
		webUtil.dynamicXpathConversion(commonPath, tabname.getTabName()).click();
	}
	
	public void signOutOfApp(WebDriverUtility webUtil) {
		webUtil.mouseHover(adminIcon);
		signOutLink.click();
	}
	
	public void selectFromQuickCreate(WebDriverUtility webUtil, String value) {
		webUtil.dropdown(quickCreateDD, value);
	}
}
