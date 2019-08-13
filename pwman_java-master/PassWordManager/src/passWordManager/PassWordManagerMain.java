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
 * This class is the main module as the
 * name states. There might not be too 
 * many changes here.
 */

public class PassWordManagerMain {

	// Initializer, not used?
	public PassWordManagerMain() {
		// TODO Auto-generated constructor stub
	}

	// The usual main method, moves conrol to other classe of 
	// the program
	public static void main(String[] args) {
		
		PassWordManagerWindowManager.launchGraphicalUserInterface();
	}

}
