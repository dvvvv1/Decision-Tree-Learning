/**
 * This is the algorithm class of Decision Tree Learning.
 * @author pz.yao
 *
 */

package algorithms;

import java.util.Vector;
import utils.TreeNode;

public class DTL {

	/**
	 * This is the base method of decision tree learning
	 * @param Data
	 * @param minleaf
	 * @return
	 */
	public static TreeNode DTL(Vector<double[]> Data, int minleaf) {
		int N = Data.size();
		if (N <= minleaf || checkInput(Data) || checkOutput(Data)) {
			TreeNode leafNode = new TreeNode();
			if (uniqueMode(Data) != 0) {
				leafNode.setLabel(uniqueMode(Data));
			} else {
				leafNode.setLabel("unknown");
			}
			return leafNode;
		}
		// copy data
		Vector<double[]> copyQueue = new Vector<double[]>();
		for (int i = 0; i < Data.size(); i++) {
			double[] temp = new double[6];
			temp[0] = Data.get(i)[0];
			temp[1] = Data.get(i)[1];
			temp[2] = Data.get(i)[2];
			temp[3] = Data.get(i)[3];
			temp[4] = Data.get(i)[4];
			temp[5] = Data.get(i)[5];
			copyQueue.add(temp);
		}

		// find best split attr and value
		double[] tempValue = new double[2];
		tempValue = SplitPivot.choose_split(copyQueue);

		// crate new node and set split point
		TreeNode leafNode = new TreeNode();
		int tempInt = (int) tempValue[0];
		leafNode.setAttr(tempInt);
		leafNode.setSplitval(tempValue[1]);

		// split data and rating according to best split value
		Vector<double[]> leftData = SplitBranch.splitLeftBranch(Data, tempValue);
		Vector<double[]> rightData = SplitBranch.splitRightBranch(Data, tempValue);

		// set left node and right node
		leafNode.setLeftNode(DTL(leftData, minleaf));
		leafNode.setRightNode(DTL(rightData, minleaf));

		return leafNode;
	}
	/**
	 * This function solves the tie problem between different
	 * labels.
	 * @param Data
	 * @return
	 */
	public static double uniqueMode(Vector<double[]> Data) {
		// setup counter for unknown, AAA, AA, A, BBB, BB, B, CCC,
		int[] count = new int[8];
		// initialize count
		for (int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		// count the numbers of each label
		for (int i = 0; i < Data.size(); i++) {
			if (Data.get(i)[5] == 1) {
				count[1]++;
			} else if (Data.get(i)[5] == 2) {
				count[2]++;
			} else if (Data.get(i)[5] == 3) {
				count[3]++;
			} else if (Data.get(i)[5] == 4) {
				count[4]++;
			} else if (Data.get(i)[5] == 5) {
				count[5]++;
			} else if (Data.get(i)[5] == 6) {
				count[6]++;
			} else if (Data.get(i)[5] == 7) {
				count[7]++;
			} else {
				count[0]++;
			}
		}
		// return the most frequent label
		int tempMax = 0;
		boolean draw = false;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > count[tempMax]) {
				tempMax = i;
				draw = false;
			} else if (count[i] == count[tempMax]) {
				draw = true;
			}
		}

		// if there is no tie in the counter
		if (draw != true) {
			return tempMax;
		}
		// if there is a tie
		else {
			return 0;
		}
	}
	/**
	 * This function validate input data node.
	 * @param Data
	 * @return
	 */
	public static boolean checkInput(Vector<double[]> Data) {
		for (int i = 0; i < Data.size(); i++) {
			for (int j = i + 1; j < Data.size(); j++) {
				if (Data.get(i)[0] != Data.get(j)[0] || Data.get(i)[1] != Data.get(j)[1]
						|| Data.get(i)[2] != Data.get(j)[2] || Data.get(i)[3] != Data.get(j)[3]
						|| Data.get(i)[4] != Data.get(j)[4]) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * This function validate output data node.
	 * @param Data
	 * @return
	 */
	public static boolean checkOutput(Vector<double[]> Data) {
		for (int i = 0; i < Data.size(); i++) {
			for (int j = i + 1; j < Data.size(); j++) {
				if (Data.get(i)[5] != Data.get(j)[5]) {
					return false;
				}
			}
		}
		return true;
	}
}
