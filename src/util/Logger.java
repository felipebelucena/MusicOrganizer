package util;
/**
 * 
 * @author FrankJunior
 *
 */
public class Logger {
	
	private final static boolean INFO = false;
	private final static boolean DEBUG = true;
	private final static boolean ERROR = false;
	private final static boolean WARNING = false;
	
	public static void info(String msg){
		if (INFO) {
			System.out.println("[INFO] "+msg);
		}
	}
	
	public static void debug(String msg){
		if (DEBUG) {
			System.out.println("[DEBUG] "+msg);
		}
	}
	
	public static void error(String msg){
		if (ERROR) {
			System.out.println("[ERROR] "+msg);
		}
	}
	
	public static void warning(String msg){
		if (WARNING) {
			System.out.println("[WARNING] "+msg);
		}
	}

}
