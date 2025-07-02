/**
 * This is the base class of the split sub-branch method.
 * @author pz.yao
 */

package algorithms;

import java.util.Vector;

public class SplitBranch {
	/**
	 * This method split left sub branch data.
	 * @param Data
	 * @param tempValue
	 * @return
	 */
	public static Vector<double[]> splitLeftBranch(Vector<double[]> Data, double[] tempValue) {
		// tempValue:bestattr, bestsplitval
		Vector<double[]> LeftData = new Vector<double[]>();
		for (int i = 0; i < Data.size(); i++) {
			double[] temp = new double[6];
			int tempIndex = (int) tempValue[0];
			if (Data.get(i)[tempIndex] <= tempValue[1]) {
				temp[0] = Data.get(i)[0];
				temp[1] = Data.get(i)[1];
				temp[2] = Data.get(i)[2];
				temp[3] = Data.get(i)[3];
				temp[4] = Data.get(i)[4];
				temp[5] = Data.get(i)[5];
				LeftData.add(temp);
			}
		}
		return LeftData;
	}
	/**
	 * This method split right sub branch data.
	 * @param Data
	 * @param tempValue
	 * @return
	 */
	public static Vector<double[]> splitRightBranch(Vector<double[]> Data, double[] tempValue) {
		// tempValue:bestattr, bestsplitval
		Vector<double[]> RightData = new Vector<double[]>();
		for (int i = 0; i < Data.size(); i++) {
			double[] temp = new double[6];
			if (Data.get(i)[(int) tempValue[0]] > tempValue[1]) {
				temp[0] = Data.get(i)[0];
				temp[1] = Data.get(i)[1];
				temp[2] = Data.get(i)[2];
				temp[3] = Data.get(i)[3];
				temp[4] = Data.get(i)[4];
				temp[5] = Data.get(i)[5];
				RightData.add(temp);
			}
		}
		return RightData;
	}
}
