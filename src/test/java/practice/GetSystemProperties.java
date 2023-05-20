package practice;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetSystemProperties {
	public static void main(String[] args) {
		//System.getProperties().list(System.out);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		RemoteWebDriver rdriver=(RemoteWebDriver)driver;
		Capabilities cap=rdriver.getCapabilities();
		
		System.out.println(cap.getBrowserName());
		System.out.println(cap.getPlatform());
		System.out.println(cap.getVersion());
		
		driver.close();
		
	}

}
