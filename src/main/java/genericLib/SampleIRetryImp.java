package genericLib;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class SampleIRetryImp implements IRetryAnalyzer
{
	int count;
	int maxRetries=4;
	
	public boolean retry(ITestResult args)
	{
		if(count<maxRetries)
		{
			count++;
			return true;
		}
		return false;
	}

}
