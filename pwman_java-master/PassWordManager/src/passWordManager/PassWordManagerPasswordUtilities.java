/**
 * 
 */
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
 * This part of the application doesn't 
 * do much currently but will be 
 * appended as soon as the design team
 * gets further on their task.
 */

class PassWordManagerPasswordUtilities {

	private static int valueToReturn = 0;
	private static boolean didThisWork;
	/**
	 * 
	 */
	protected PassWordManagerPasswordUtilities() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	protected static int saveAsMenuItemClick() {
		didThisWork = false;
		
		if (didThisWork != true) {
			valueToReturn++;
		}
		return valueToReturn;
	}
	
	protected static int loadMenuItemClick() {
		didThisWork = false;
		
		if (didThisWork != true) {
			valueToReturn++;
		}
		return valueToReturn;
	}
	
	protected static int applicationInfoMenuItemClick() {
		didThisWork = false;
		
		if (didThisWork != true) {
			valueToReturn++;
		}
		return valueToReturn;
	}
	
	protected static int cancelButtonClick() {
		didThisWork = false;
		
		if (didThisWork != true) {
			valueToReturn++;
		}
		return valueToReturn;
	}
	
	protected static int okButtonClick() {
		didThisWork = false;
		
		if (didThisWork != true) {
			valueToReturn++;
		}
		return valueToReturn;
	}

}
