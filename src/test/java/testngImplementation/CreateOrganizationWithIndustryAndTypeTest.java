package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLib.BaseClass;
import genericLib.IConstantPath;
import genericLib.TabNames;

public class CreateOrganizationWithIndustryAndTypeTest extends BaseClass {

	@Test
	public void createOrgWithIndustryAndTypeTest() {
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		
		org.clickPlusButton();
		soft.assertTrue(createOrg.getPageHeader().contains("Creating"));
		
		Map<String, String> map = excel.getDataFromExcel("OrganizationsTestData",
				"Create Organization With Industry And Type");
		String orgName = map.get("Organization Name") + jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(webUtil, map.get("Industry"));
		createOrg.selectType(webUtil, map.get("Type"));

		createOrg.clickSaveButton();
		soft.assertTrue(newOrgInfo.getPageHeader().contains(orgName));
		if (newOrgInfo.getPageHeader().contains(orgName))
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass", IConstantPath.EXCEL_PATH);
		else 
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail", IConstantPath.EXCEL_PATH);
	
		soft.assertAll();
	}

}
