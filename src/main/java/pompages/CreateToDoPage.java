package pompages;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLib.WebDriverUtility;

public class CreateToDoPage {

	@FindBy(xpath = "//b[text()='Create To Do']")
	private WebElement pageHeader;
	
	@FindBy(name = "subject")
	private WebElement subjectTF;
	
	@FindBy(xpath = "//img[@id='jscal_trigger_date_start']")
	private WebElement startDatePicker;
	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")
	private WebElement calenderHeader;
	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']")
	private WebElement nextYearButton;
	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='›']")
	private WebElement nextMonthButton;
	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='‹']")
	private WebElement previousMonthButton;
	
	private String date = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='%s']";
	
	@FindBy(xpath = "//img[@id='jscal_trigger_due_date']")
	private WebElement dueDatePicker;
	
	@FindBy(xpath = "//input[@value='  Save']")
	private WebElement saveButton;
	
	public CreateToDoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setSubject(String subject) {
		subjectTF.sendKeys(subject);
	}
	
	public void clickStartDatePicker() {
		startDatePicker.click();
	}
	
	public void clickDueDatePicker() {
		dueDatePicker.click();
	}
	
	public void datePicker(String reqDate, WebDriverUtility web) {
		String[] s = reqDate.split("-");
		String reqDate2 = s[2];
		int reqMonth2 =Integer.parseInt(s[1]);
		int reqYear2 = Integer.parseInt(s[0]);
		
		String[] str2 = calenderHeader.getText().split(", ");
		
		int actYear2 = Integer.parseInt(str2[1]);
		
		while(actYear2 < reqYear2) {
			nextYearButton.click();
			str2 = calenderHeader.getText().split(", ");
			actYear2 = Integer.parseInt(str2[1]);	
		}
		
		int actMonth2 = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str2[0]).get(ChronoField.MONTH_OF_YEAR);
		
		while(actMonth2 < reqMonth2) {
			nextMonthButton.click();
			str2 = calenderHeader.getText().split(", ");
			actMonth2 = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str2[0]).get(ChronoField.MONTH_OF_YEAR);
		}
		
		while(actMonth2 > reqMonth2) {
			previousMonthButton.click();
			str2 = calenderHeader.getText().split(", ");
			actMonth2 = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str2[0]).get(ChronoField.MONTH_OF_YEAR);
		}
		
		web.dynamicXpathConversion(date, reqDate2).click();
		
	}
	
	public void clickSave() {
		saveButton.click();
	}
}
