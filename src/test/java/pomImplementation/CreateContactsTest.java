package pomImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLib.ExcelUtility;
import genericLib.IConstantPath;
import genericLib.JavaUtility;
import genericLib.PropertiesUtility;
import genericLib.TabNames;
import genericLib.WebDriverUtility;
import pompages.ContactsPage;
import pompages.CreateContactPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewContactInfoPage;

public class CreateContactsTest {

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
		ContactsPage contact = new ContactsPage(driver);
		CreateContactPage createContact = new CreateContactPage(driver);
		NewContactInfoPage newContactInfo = new NewContactInfoPage(driver);
		
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
		
		home.clickRequiredTab(TabNames.CONTACTS, webUtil);
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts page displayed");
		else
			System.out.println("Contacts page not found");
		
		contact.clickPlusButton();
		
		if(createContact.getPageHeader().contains("Creating"))
			System.out.println("Creating new organization page displayed");
		else
			System.out.println("Creating new organization page not displayed");

		Map<String,String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String contactName = map.get("Last Name")+jutil.generateRandomNumber(100);
		createContact.setLastName(contactName);
		createContact.clickSave();
		
		if(newContactInfo.getPageHeader().contains(contactName)) {
			System.out.println("New contact created");
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("New contact not created");
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "Fail", IConstantPath.EXCEL_PATH);
		}
		
		home.signOutOfApp(webUtil);		
		webUtil.closeAllWindows();
		excel.closeExcel();
	}

}
