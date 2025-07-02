/**
 * This class is used for loading the training and test data.
 * @author pz.yao
 */

package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LoadData {
	
	/**
	 * This function loads training data based on input parameters.
	 * @param parameters
	 * @param labelOfTrain
	 * @param dataOfTrain
	 * @throws IOException
	 */
	public static void LoadTrainData(String[] parameters, Vector<String> labelOfTrain, Vector<double[]> dataOfTrain) throws IOException {
		// read train data from text file
		java.io.FileReader fr = new FileReader(parameters[0]);
		BufferedReader reader = new BufferedReader(fr);

		// create temporal container for
		// line reading
		String line1;

		// read first line
		// store label into string vector
		if ((line1 = reader.readLine()) != null) {
			String[] part = line1.split("\\s");
			for (int i = 0; i < part.length; i++) {
				if (!part[i].equals("")) {
					labelOfTrain.add(part[i]);
				}
			}
		}

		// read data value
		while ((line1 = reader.readLine()) != null) {
			// setup
			// WC_TA, RE_TA, EBIT_TA, MVE_BVTD, S_TA
			// Rating:AAA=1, AA=2, A=3, BBB=4, BB=5, B=6, C=7, unknown = 0;
			double[] temp = new double[6];
			String[] part2 = line1.split("\\s");
			int tempIndex = 0;
			for (int i = 0; i < part2.length; i++) {
				if (!part2[i].equals("") && i < part2.length - 1) {
					double floatReader = Double.valueOf(part2[i]);
					temp[tempIndex] = floatReader;
					tempIndex++;
				} else if (!part2[i].equals("") && i == part2.length - 1) {
					if (part2[i].equals("AAA")) {
						temp[tempIndex] = 1;
					} else if (part2[i].equals("AA")) {
						temp[tempIndex] = 2;
					} else if (part2[i].equals("A")) {
						temp[tempIndex] = 3;
					} else if (part2[i].equals("BBB")) {
						temp[tempIndex] = 4;
					} else if (part2[i].equals("BB")) {
						temp[tempIndex] = 5;
					} else if (part2[i].equals("B")) {
						temp[tempIndex] = 6;
					} else if (part2[i].equals("CCC")) {
						temp[tempIndex] = 7;
					} else {
						temp[tempIndex] = 0;
					}
				}
			}
			dataOfTrain.add(temp);
		}
		// read train data end
		reader.close();
		fr.close();

	}
	
	/**
	 * This function loads test data based on input parameters.
	 * @param parameters
	 * @param labelOfTest
	 * @param dataOfTest
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void LoadTestData(String[] parameters, Vector<String> labelOfTest, Vector<double[]> dataOfTest) throws NumberFormatException, IOException {
		// read test data
		java.io.FileReader fr2 = new FileReader(parameters[1]);
		BufferedReader reader2 = new BufferedReader(fr2);

		// create vector to store data
		String line2;

		// read first line
		// store label into string vector
		if ((line2 = reader2.readLine()) != null) {
			String[] part = line2.split("\\s");
			for (int i = 0; i < part.length; i++) {
				if (!part[i].equals("")) {
					labelOfTest.add(part[i]);
				}
			}
		}

		// read data value
		while ((line2 = reader2.readLine()) != null) {
			// setup
			// WC_TA, RE_TA, EBIT_TA, MVE_BVTD, S_TA
			double[] temp = new double[5];
			String[] part2 = line2.split("\\s");
			int tempIndex = 0;
			for (int i = 0; i < part2.length; i++) {
				if (!part2[i].equals("") && i < part2.length) {
					double floatReader = Double.valueOf(part2[i]);
					temp[tempIndex] = floatReader;
					tempIndex++;
				}
			}
			dataOfTest.add(temp);
		}
		// read test data end
		reader2.close();
		fr2.close();
	}

}
