package hardcodedTests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement loginHeader = driver.findElement(By.xpath("//a[text()='vtiger']"));
		if(loginHeader.getText().equals("vtiger"))
			System.out.println("Login page is displayed");
		else
			System.out.println("Login page not found");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).submit();
		
		WebElement homeHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(homeHeader.getText().contains("Home"))
			System.out.println("Home Page Displayed");
		else
			System.out.println("Home Page Not Found");
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		WebElement leadsHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(leadsHeader.getText().contains("Leads"))
			System.out.println("Leads Page Displayed");
		else
			System.out.println("Leads Page Not Found");
		
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		WebElement createLeadHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(createLeadHeader.getText().contains("Creating New Lead"))
			System.out.println("Create Lead Page Displayed");
		else
			System.out.println("Create Lead Page Not Found");
		
		Random random = new Random();
		String lastName = "Pqr" + random.nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		String company = "TCS"+ random.nextInt(100);
		driver.findElement(By.name("company")).sendKeys(company);
		
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(newLeadInfoHeader.getText().contains(lastName))
			System.out.println("New Lead Created Successfully");
		else
			System.out.println("New Lead Not Created");
		
		driver.findElement(By.name("Duplicate")).click();
		
		WebElement duplicatingHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(duplicatingHeader.getText().contains("Duplicating \""+lastName+"\""))
			System.out.println("Duplicating page Displayed");
		else
			System.out.println("Duplicating page not found");
		
		String newLastName = "Mno"+random.nextInt(100);
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys(newLastName);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(duplicateLeadInfoHeader.getText().contains(newLastName))
			System.out.println("New Lead Duplicated Successfully");
		else
			System.out.println("New Lead Not Duplicated");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\""));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.close();
		
		
		
		
	}

}
