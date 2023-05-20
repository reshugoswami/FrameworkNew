package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLib.ExcelUtility;
import genericLib.IConstantPath;
import genericLib.JavaUtility;
import genericLib.PropertiesUtility;
import genericLib.TabNames;
import genericLib.WebDriverUtility;
import pompages.CreateNewOrganizationPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationsPage;

public class CreateOrganizationTest {

	public static void main(String[] args) {
		PropertiesUtility property = new PropertiesUtility();
		JavaUtility jutil = new JavaUtility();
		ExcelUtility excel = new ExcelUtility();
		WebDriverUtility webUtil = new WebDriverUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = webUtil.openApplication(browser, url, time);
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationsPage org = new OrganizationsPage(driver);
		CreateNewOrganizationPage createOrg = new CreateNewOrganizationPage(driver);
		NewOrganizationInfoPage newOrgInfo = new NewOrganizationInfoPage(driver);
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not found");
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home page is displayed");
		else
			System.out.println("Home page not found");
	
		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations page displayed");
		else
			System.out.println("Organizations page not found");
		
		org.clickPlusButton();
		
		if(createOrg.getPageHeader().contains("Creating"))
			System.out.println("Creating new organization page displayed");
		else
			System.out.println("Creating new organization page not displayed");
		
		Map<String,String> map = excel.getDataFromExcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.clickSaveButton();
		
		if(newOrgInfo.getPageHeader().contains(orgName)) {
			System.out.println("New organization created");
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("New organization not created");
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "Fail", IConstantPath.EXCEL_PATH);
		}
		
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();

	}

}
