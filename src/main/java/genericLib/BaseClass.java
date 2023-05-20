package genericLib;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.ContactsPage;
import pompages.CreateContactPage;
import pompages.CreateNewOrganizationPage;
import pompages.CreateToDoPage;
import pompages.EventInfoPage;
import pompages.HomePage;
import pompages.LoginPage;
import pompages.NewContactInfoPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationsPage;

public class BaseClass {
	
	protected PropertiesUtility property;
	protected JavaUtility jutil;
	protected ExcelUtility excel;
	protected WebDriverUtility webUtil;
	protected WebDriver driver;
	protected LoginPage login;
	protected HomePage home;
	protected ContactsPage contact;
	protected CreateContactPage createContact;
	protected NewContactInfoPage newContactInfo;
	protected CreateToDoPage createToDo;
	protected EventInfoPage eventInfo;
	protected OrganizationsPage org;
	protected CreateNewOrganizationPage createOrg;
	protected NewOrganizationInfoPage newOrgInfo;
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	//@BeforeSuite
	//@BeforeTest
	@BeforeClass
	public void classConfiguration() {
		property = new PropertiesUtility();
		jutil = new JavaUtility();
		excel = new ExcelUtility();
		webUtil = new WebDriverUtility();
		sjutil=jutil;
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		driver = webUtil.openApplication(browser, url, time);
		sdriver=driver;
		Assert.assertTrue(driver.getTitle().contains("vtiger"));
	}
	
	@BeforeMethod
	public void methodConfiguration() {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		contact = new ContactsPage(driver);
		createContact = new CreateContactPage(driver);
		newContactInfo = new NewContactInfoPage(driver);
		createToDo = new CreateToDoPage(driver);
		eventInfo = new EventInfoPage(driver);
		org = new OrganizationsPage(driver);
		createOrg = new CreateNewOrganizationPage(driver);
		newOrgInfo = new NewOrganizationInfoPage(driver);
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		Assert.assertTrue(driver.getTitle().contains("Home"));
	}
	
	@AfterMethod
	public void methodTearDown() {
		home.signOutOfApp(webUtil);	
	}
	
	@AfterClass
	public void classTearDown() {
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
	
	//@AfterTest
	//@AfterSuite
}




