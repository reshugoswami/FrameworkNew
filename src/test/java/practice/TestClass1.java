package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.SampleBaseClass;

public class TestClass1 extends SampleBaseClass
{
	@Test
	public void test1()
	{
		System.out.println("test1");
	}
	@Test(retryAnalyzer=genericLib.SampleIRetryImp.class)  ///right click on sampleIRetryImp class name click copy qulified name  
	public void test2()
	
	{
	System.out.println("test2");
	Assert.fail();
	}

}
