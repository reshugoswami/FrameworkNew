package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLib.BaseClass;
import genericLib.IConstantPath;
import genericLib.TabNames;

public class CreateContactWithOrgTest extends BaseClass {

	@Test
	public void createContactWithOrgTest() {
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.CONTACTS, webUtil);
		soft.assertTrue(driver.getTitle().contains("Contacts"));
		
		contact.clickPlusButton();
		soft.assertTrue(createContact.getPageHeader().contains("Creating"));
		
		Map<String,String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact With Organization");
		String contactName = map.get("Last Name")+jutil.generateRandomNumber(100);
		createContact.setLastName(contactName);
		createContact.selectExistingOrganization(webUtil, map.get("Organization Name"));
		createContact.clickSave();

		soft.assertTrue(newContactInfo.getPageHeader().contains(contactName));
		if(newContactInfo.getPageHeader().contains(contactName)) 
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "Pass", IConstantPath.EXCEL_PATH);
		else 
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "Fail", IConstantPath.EXCEL_PATH);
	
		soft.assertAll();
	}

}
