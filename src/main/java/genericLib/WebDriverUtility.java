package genericLib;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods to perform operations on web driver
 * 
 * @author CBT
 *
 */
public class WebDriverUtility {

	private WebDriver driver;

	/**
	 * This method is used to launch the browser
	 * @param browser
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser data");
		}
		return driver;
	}
	
	/**
	 * This method is used to maximize the browser
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to navigate to the application 
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}
	
	/**
	 * This method waits until element is found
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	/**
	 * This method navigates to application
	 * @param browser
	 * @param url
	 * @param time
	 * @return
	 */
	public WebDriver openApplication(String browser, String url, long time) {
		WebDriver driver = launchBrowser(browser);
		maximizeBrowser();
		navigateToApp(url);
		waitTillElementFound(time);
		return driver;
	}
	
	/**
	 * This method returns WebElement if the element is visible
	 * @param time
	 * @param element
	 * @return 
	 */
	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method returns WebElement if it is enabled
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method returns true if the web page title is displayed 
	 * @param time
	 * @param title
	 * @return
	 */
	public boolean explicitWait(long time, String title) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	/**
	 * This method is used to mouse hover on an element
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}
	/**
	 * This method is used to right click on an element
	 * @param element
	 */
	public void rightClick(WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}
	
	/**
	 * This method is used to double click on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to drag and drop an element
	 * @param element
	 */
	public void dragAndDropElement(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}
	
	/**
	 * This method is used to select an element from drop down using index
	 * @param element
	 * @param index
	 */
	public void dropdown(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method is used to select an element from drop down using value 
	 * @param element
	 * @param value
	 */
	public void dropdown(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * This method is used to select an element from drop down using text
	 * @param text
	 * @param element
	 */
	public void dropdown(String text, WebElement element) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * This method is used to switch to frame using index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch to frame using id or name attribute value
	 * @param idOrName
	 */
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	
	/**
	 * This method is used to scroll till the element
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	/**
	 * This method captures the screenshot and returns the absolute path of the screenshot file
	 * @param screenshotPath
	 * @return
	 */
	public String getScreenshot(String className,WebDriver driver,JavaUtility jutil) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot"+className+"_"+jutil.getCurrentTime()+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest.getAbsolutePath();
	}
	public String getScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}
	
	/**
	 * This method is used to handle alert pop up
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert al = driver.switchTo().alert();
		if(status.equalsIgnoreCase("OK"))
			al.accept();
		else
			al.dismiss();
	}
	
	/**
	 * This method is used to switch to child window
	 */
	public void childBrowserPopUp() {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String id : windowIDs) {
			driver.switchTo().window(id);
		}
	}
	
	/**
	 * This method is used to get parent window id
	 * @return
	 */
	public String getParentWindowID() {
		return driver.getWindowHandle();
	}
	
	/**
	 * This method is used to switch to required window
	 * @param id
	 */
	public void switchToWindow(String id) {
		driver.switchTo().window(id);
	}
	
	/**
	 * This method is used to convert dynamic xpath to WebElement
	 * @param dynamicPath
	 * @param replaceData
	 * @return
	 */
	public WebElement dynamicXpathConversion(String dynamicPath, String replaceData) {
		String requiredPath = String.format(dynamicPath, replaceData);
		WebElement element = driver.findElement(By.xpath(requiredPath));
		return element;
	}
	
	/**
	 * This method is used to close current window
	 */
	public void closeCurrentWindow() {
		driver.close();
	}
	
	/**
	 * This method is used to close all the windows
	 */
	public void closeAllWindows() {
		driver.quit();
	}
}
