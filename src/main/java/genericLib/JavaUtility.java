package genericLib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains reusable methods to perform java related operations
 * @author CBT
 *
 */
public class JavaUtility {
	
	/**
	 * This method generates and returns random number within specified limit
	 * @param limit
	 * @return
	 */
	public int generateRandomNumber(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}

	/**
	 * This method is used to return the current time
	 * @return
	 */
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_mm_sss");
		return sdf.format(date);
	}
}
