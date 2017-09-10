package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration {
	/*
	 * store current config argument
	 */
	public static String[] args = new String[3];

	/**
	 * Constructor
	 * read configuration info from config file
	 */
	public Configuration() {
		// setup new configuration file name
		String configFileName = "Config";

		// open configuration file
		File file = new File(configFileName);
		BufferedReader reader = null;
		// setup parameters' container
		String DTL_Train_File = null;
		String DTL_Test_File = null;
		String DTL_Minleaf = null;
		
		// start read configuration info from configuration file
		try {
			// setup new reader and text container
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			// start reading configuration file
			while ((text = reader.readLine()) != null) {
				// read line text
				// jump out if this line contains no key info
				if(text.equals("") || text.charAt(0) == '%' || text.equals("\t") || text.equals(" ") || text.charAt(0) == ' ') {
					// check to see this line 
					// is instruction comment
					continue;
				}
				else {
					if(text.contains("DTL_Train_File")) {
						// setup scan pattern
						String regex = "\"([^\"]*)\"";
						// set pattern to read between double quotes
						Pattern pat = Pattern.compile(regex);
						Matcher m = pat.matcher(text);
						if(m.find()) {
							// find SPP_Map_File name between quotes
							DTL_Train_File = m.group(1);
						}					
					}
					else if(text.contains("DTL_Test_File")) {
						// setup scan pattern
						String regex = "\"([^\"]*)\"";
						// set pattern to read between double quotes
						Pattern pat = Pattern.compile(regex);
						Matcher m = pat.matcher(text);
						if(m.find()) {
							// find Approach between quotes
							DTL_Test_File = m.group(1);
						}
					}
					else if(text.contains("DTL_Minleaf")) {
						// setup scan pattern
						String regex = "\"([^\"]*)\"";
						// set pattern to read between double quotes
						Pattern pat = Pattern.compile(regex);
						Matcher m = pat.matcher(text);
						if(m.find()) {
							// find AStarParameter between quotes
							DTL_Minleaf = m.group(1);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					// reading finish
					// close reader
					reader.close();
				}
			} catch (IOException e) {
			}
		}

		// setup arguments
		args[0] = DTL_Train_File;
		args[1] = DTL_Test_File;
		args[2] = DTL_Minleaf;
	}

	/**
	 * Get current config arguments
	 * @return
	 */
	public String[] getConfig() {
		return this.args;
	}
}
