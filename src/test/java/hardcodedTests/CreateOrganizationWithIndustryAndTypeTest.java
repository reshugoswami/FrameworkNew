package hardcodedTests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithIndustryAndTypeTest {

	public static void main(String[] args) {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement loginPage=driver.findElement(By.xpath("//a[text()='vtiger']"));
		if(loginPage.getText().equals("vtiger"))
			System.out.println("login homepage is display");
		else
			System.out.println("login home page is not display");
		
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
		
		Random random = new Random();
		int randomNum = random.nextInt(100);
		String orgName = "TCS"+randomNum;
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
		Actions a = new Actions(driver);
		a.moveToElement(administratorIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}

}
