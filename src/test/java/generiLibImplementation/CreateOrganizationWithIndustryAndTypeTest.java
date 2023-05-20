package generiLibImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import genericLib.ExcelUtility;
import genericLib.IConstantPath;
import genericLib.JavaUtility;
import genericLib.PropertiesUtility;
import genericLib.WebDriverUtility;

public class CreateOrganizationWithIndustryAndTypeTest {

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
		
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.get("http://localhost:8888/");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not found");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).submit();
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home page is displayed");
		else
			System.out.println("Home page not found");
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations page displayed");
		else
			System.out.println("Organizations page not found");
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String createOrgPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(createOrgPageHeader.contains("Creating"))
			System.out.println("Creating new organization page displayed");
		else
			System.out.println("Creating new organization page not displayed");
		
		//Random random = new Random();
		Map<String,String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		
		
		String orgName = map.get("orgName")+jutil.generateRandomNumber(100);
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select s = new Select(industryDropdown);
		s.selectByValue("Banking");
		
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select s1 = new Select(typeDropdown);
		s1.selectByValue("Investor");
		
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		
		String newOrgInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newOrgInfoHeader.contains(orgName))
			System.out.println("New organization created");
		else
			System.out.println("New organization not created");
		
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		String newOrg = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]/a")).getText();
		if(newOrg.equals(orgName))
			System.out.println("Test Pass");
		else
			System.out.println("Test Fail");
		
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	//	Actions a = new Actions(driver);
		//a.moveToElement(administratorIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//driver.quit();
		
		webUtil.closeAllWindows();
		excel.closeExcel();

	}

}
