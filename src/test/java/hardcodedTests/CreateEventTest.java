package hardcodedTests;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateEventTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement loginPage=driver.findElement(By.xpath("//a[text()='vtiger']"));
		if(loginPage.getText().equals("vtiger"))
			System.out.println("login homepage is display");
		else
			System.out.println("login home page is not display");
	/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.cssSelector("[id='submitButton']")).submit();
		
		WebElement homePage=driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if(homePage.getText().contains("Home"))
		System.out.println("vtiger home page is display");
		else
			
			System.out.println("vtiger home page is not display");
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		WebElement dropdown=driver.findElement(By.cssSelector("[id='qccombo']"));
		Select d=new Select(dropdown);
		
		d.selectByValue("Events");
		Thread.sleep(2000);
		
		String createPage=driver.findElement(By.xpath("//b[text()='Create To Do']")).getText(); //get text return type is String not webElement
		if(createPage.equals("Create To Do"))
			System.out.println("create even page is display");
		else
			System.out.println("create even page is not display");
		
		
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("meeting");
		
		WebElement dropdown1=driver.findElement(By.xpath("//select[@name='eventstatus']"));
		 
		Select d1=new Select(dropdown1);
		d1.selectByIndex(2);
		driver.findElement(By.xpath("//table[@class='qcTransport']/descendant::input[@class='crmbutton small save']")).click();
		
		driver.findElement(By.id("jscal_trigger_date_start")).click();
		
		int reqDate=28;
		int reqMonth=10;
		int reqYear=2028;
		String calenderHeader=driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
		String[] str=calenderHeader.split(" ");
		
		int actYear=Integer.parseInt(str[1]);
		
		while(actYear<reqYear)
		{
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']")).click();
			calenderHeader=driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
			str=calenderHeader.split(", ");
			actYear=Integer.parseInt(str[1]);
			
		}
		int actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
				
	while(actMonth<reqMonth)
	{
		driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='›']")).click();
		calenderHeader=driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
		str=calenderHeader.split(", ");
		
		actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
			
	}
		
	driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='"+reqDate+"']")).click();	
		
	Thread.sleep(2000);
	WebElement adminIcon=driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	Actions a=new Actions(driver);
	a.moveToElement(adminIcon).perform();
	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();	
		
		
		driver.quit();
		
	}
	}

