/**
 * This is the base class of the split value algorithm.
 * @author pz.yao
 */

package algorithms;

import java.util.Vector;

public class SplitPivot {
	/**
	 * This function sets split value of the node.
	 * @param Data
	 * @return
	 */
	public static double[] choose_split(Vector<double[]> Data) {
		// initialize
		// bestAttr & bestSplitVal
		double[] result = new double[2];
		double bestGain = 0;

		// find best attr and split value
		for (int i = 0; i < Data.get(0).length - 1; i++) {
			// sort attr data
			Vector<double[]> sortedData = SortTreeData.sortData(Data, i);
			for (int j = 0; j < sortedData.size() - 1; j++) {
				double temp = sortedData.get(j)[i] + sortedData.get(j + 1)[i];
				double tempSplitVal = 0.5 * temp;
				double tempGain = GainCalculation.calGain(sortedData, i, tempSplitVal);
				if (tempGain > bestGain) {
					bestGain = tempGain;
					result[0] = i;// bestAttr
					result[1] = tempSplitVal;// bestSplitVal
				}
			}
		}
		return result;
	}
}
