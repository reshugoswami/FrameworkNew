package genericLib;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SampleBaseClass {
@BeforeSuite
	public void suite() {
			System.out.println("BeforeSuite");
		}
	@BeforeTest
	public void test() {
			System.out.println("BeforeTest");
		}
	@BeforeClass
	public void classConfig()
	{
			System.out.println("Beforelass");
		}
	@BeforeMethod
	public void method() {
			System.out.println("BeforeMethod");
		}
	@AfterClass
	public void classTD() {
			System.out.println("AfterClass");
		}
	@AfterTest
	public void testTD() {
			System.out.println("AfterTest");
		}
	@AfterSuite
	public void suiteTD() {
			System.out.println("AfterSuite");
		}
	
	}


