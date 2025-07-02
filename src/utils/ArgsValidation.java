/**
 * Argument validation
 * @author pz.yao
 */

package utils;

public class ArgsValidation {
	private static String argError01 = "Argument Length Error";
	private static String argError02 = "MinLeaf value Error";
	/**
	 * Arguments validation
	 * @param parameters
	 */
	public static void ArgsValidation(String[] parameters) {
		// read method
		if (parameters.length != 3) {
			System.out.println(argError01);
			System.exit(0);
		} else if (Integer.valueOf(parameters[2]) == null) {
			System.out.println(argError02);
			System.exit(0);
		}
	}
}
