package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLib.BaseClass;
import genericLib.IConstantPath;

public class CreateEventTest extends BaseClass {

	@Test
	public void createEventTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		
		Map<String, String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
		home.selectFromQuickCreate(webUtil, map.get("Quick Create"));
		soft.assertEquals("Create To Do", createToDo.getPageHeader());
		
		String subject = map.get("Subject")+ jutil.generateRandomNumber(100);
		createToDo.setSubject(subject);
		createToDo.clickStartDatePicker();
		createToDo.datePicker(map.get("Start Date"), webUtil);
		Thread.sleep(2000);
		createToDo.clickDueDatePicker();
		createToDo.datePicker(map.get("Due Date"), webUtil);
		createToDo.clickSave();
		
		soft.assertTrue(eventInfo.getPageHeader().contains(subject));
		if(eventInfo.getPageHeader().contains(subject)) 
			excel.writeDataToExcel("EventsTestData", "Create New Event", "Pass", IConstantPath.EXCEL_PATH);
		else 
			excel.writeDataToExcel("EventsTestData", "Create New Event", "Fail", IConstantPath.EXCEL_PATH);
	
		soft.assertAll();
	}

}
