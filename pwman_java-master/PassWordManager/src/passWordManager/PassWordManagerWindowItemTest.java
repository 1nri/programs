package passWordManager;

/**
 * @author henrijuvonen
 * Created on 10th of July 2019.
 * This project aims to reconstruct and 
 * improve  an old utility program 
 * PasswordManager I made with Netbeans 
 * in 2015 or so. Part of the code was 
 * no longer working so I'll try to 
 * make this version a more generic 
 * one.
 * 
 * This test class doesn't currently 
 * provide correct results although 
 * the application might work as it 
 * should. That is due to a lack of 
 * implementation at this point of 
 * time. This test will work better as 
 * implementation progresses further.
 * 
 * edited 22.7.2019 : modified class
 * specific comments of return values
 */

class PassWordManagerWindowItemTest {

	private static String testBegins = " tested";
	private static String testEnds = " testing ended";
	private static String testWorks = "seems to work nicely";
	private static String testFails = "seems this is lacking something";

	/**
	 * @param args none
	 * @return Each method eturns a respective string containing test 
	 * result (button works or doesn't)
	 */
	
	protected static void confirmTest() {
		
		System.out.println("OK" + testBegins);
		
		if(PassWordManagerPasswordUtilities.okButtonClick() == 0) {
			System.out.println(testWorks);
		}
		else {
			System.out.println(testFails);
		}
		System.out.println("OK" + testEnds);
	}
	
	protected static void cancelTest() {
		
		System.out.println("Cancel" + testBegins);
		if(PassWordManagerPasswordUtilities.cancelButtonClick() == 0) {
			System.out.println(testWorks);
		}
		else {
			System.out.println(testFails);
		}
		System.out.println("Cancel" + testEnds);
	}
	
	protected static void fileMenuTest() {
		
		System.out.println("File menu" + testBegins);
		System.out.println(testWorks);
	}
	
	protected static void openFileTest() {
		
		System.out.println("Opening a file" + testBegins);
		if(PassWordManagerPasswordUtilities.loadMenuItemClick() == 0) {
			System.out.println(testWorks);
		}
		else {
			System.out.println(testFails);
		}
		System.out.println("Opening a file" + testEnds);
	}
	
	protected static void saveFileAsTest() {
		
		System.out.println("Saving a new file" + testBegins);
		if(PassWordManagerPasswordUtilities.saveAsMenuItemClick() == 0) {
			System.out.println(testWorks);
		}
		else {
			System.out.println(testFails);
		}
		System.out.println("Saving a new file" + testEnds);
	}
	
	protected static void helpMenuTest() {
		
		System.out.println("Help menu" + testBegins);
		System.out.println(testWorks);
	}
	
	protected static void applicationInfoTest() {
		
		System.out.println("Application Information" + testBegins);
		if(PassWordManagerPasswordUtilities.applicationInfoMenuItemClick() == 0) {
			System.out.println(testWorks);
		}
		else {
			System.out.println(testFails);
		}
		System.out.println("Application Information" + testEnds);
	}

}
