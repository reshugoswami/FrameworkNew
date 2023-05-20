package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLib.BaseClass;
import genericLib.IConstantPath;
import genericLib.TabNames;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void createOrganizationTest() {
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		
		org.clickPlusButton();
		soft.assertTrue(createOrg.getPageHeader().contains("Creating"));
		
		Map<String,String> map = excel.getDataFromExcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.clickSaveButton();
		
		soft.assertTrue(newOrgInfo.getPageHeader().contains(orgName));
		if(newOrgInfo.getPageHeader().contains(orgName)) 
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "Pass", IConstantPath.EXCEL_PATH);
		else 
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "Fail", IConstantPath.EXCEL_PATH);
		
		soft.assertAll();
	}

}
