package generiLibImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLib.ExcelUtility;
import genericLib.IConstantPath;
import genericLib.JavaUtility;
import genericLib.PropertiesUtility;
import genericLib.WebDriverUtility;

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
		
//		Random random = new Random();
//		int randomNum = random.nextInt(100);
//		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
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
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts page displayed");
		else
			System.out.println("Contacts page not found");
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		String createOrgPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"))
									.getText();
		if(createOrgPageHeader.contains("Creating"))
			System.out.println("Creating new organization page displayed");
		else
			System.out.println("Creating new organization page not displayed");

		Map<String,String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String contactName = map.get("Last Name")+jutil.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		
		String newContactInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newContactInfoHeader.contains(contactName))
			System.out.println("New contact created");
		else
			System.out.println("New contact not created");
		
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
		String newContact = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[4]/a"))
						.getText();
		if(newContact.equals(contactName)) {
			System.out.println("Test Pass");
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "Pass", IConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("Test Fail");
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "Fail", IConstantPath.EXCEL_PATH);
		}
		
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a = new Actions(driver);
//		a.moveToElement(administratorIcon).perform();
		webUtil.mouseHover(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		driver.quit();
		
		webUtil.closeAllWindows();
		excel.closeExcel();
	}

}
