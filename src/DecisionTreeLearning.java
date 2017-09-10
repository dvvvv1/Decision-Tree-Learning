import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;

import algorithms.DTL;
import utils.ArgsValidation;
import utils.Configuration;
import utils.LoadData;
import utils.TreeNode;

/**
 * Arguments: 
 * DTL_Train_File = "train"; 
 * DTL_Test_File = "test"; 
 * DTL_Minleaf = "20";
 * 
 * @author puzhiyao 
 * Email: puzhi.yao@gmail.com 
 * Coding Date: 19 April 2015
 *
 */
public class DecisionTreeLearning {
	/*
	 * Number of branches
	 */
	public static int depth;
	/*
	 * Save the order of each node
	 */
	public static Vector<int[]> queueList = new Vector<int[]>();

	/**
	 * This is the driver function of Decision Tree Learning: Predict Credit
	 * Ratio
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Run configuration
		Configuration config = new Configuration();
		String[] parameters = config.getConfig();
		
		// Validate input arguments
		ArgsValidation.ArgsValidation(parameters);
		int minLeaf = Integer.valueOf(parameters[2]);
		
		// Setup data and label container
		Vector<String> dabelOfTrain = new Vector<String>();
		Vector<double[]> dataOfTrain = new Vector<double[]>();
		
		Vector<String> labelOfTest = new Vector<String>();
		Vector<double[]> dataOfTest = new Vector<double[]>();
		
		// Load train data
		LoadData.LoadTrainData(parameters, dabelOfTrain, dataOfTrain);

		// Load test data
		LoadData.LoadTestData(parameters, labelOfTest, dataOfTest);
		
		// Run DTL algorithm
		TreeNode root = DTL.DTL(dataOfTrain, minLeaf);

		// Debug Code
		// printResult(dataOfTrain);
		// Vector<TreeNode> queue = new Vector<TreeNode>();
		// int[] first_count = new int[1];
		// first_count[0] = 1;
		// queueList.add(first_count);
		// queue.add(root);
		// depth = 1;
		// printDT(queue);

		// predict new data
		// System.out.println("size of test data: "+dataOfTest.size());
		for (int i = 0; i < dataOfTest.size(); i++) {
			System.out.println(predict(root, dataOfTest.get(i)));
		}

	}
	/**
	 * This function prints prediction results of the decision tree model
	 * @param Data
	 */
	public static void printResult(Vector<double[]> Data) {
		for (int i = 0; i < Data.size(); i++) {
			System.out.print(Data.get(i)[0] + " ");
			System.out.print(Data.get(i)[1] + " ");
			System.out.print(Data.get(i)[2] + " ");
			System.out.print(Data.get(i)[3] + " ");
			System.out.print(Data.get(i)[4] + " ");
			if (Data.get(i)[5] == 0) {
				System.out.println("unknown");
			} else if (Data.get(i)[5] == 1) {
				System.out.println("AAA");
			} else if (Data.get(i)[5] == 2) {
				System.out.println("AA");
			} else if (Data.get(i)[5] == 3) {
				System.out.println("A");
			} else if (Data.get(i)[5] == 4) {
				System.out.println("BBB");
			} else if (Data.get(i)[5] == 5) {
				System.out.println("BB");
			} else if (Data.get(i)[5] == 6) {
				System.out.println("B");
			} else if (Data.get(i)[5] == 7) {
				System.out.println("CCC");
			}
		}
	}
	/**
	 * This function returns the prediction of the node.
	 * @param currentTreeNode
	 * @param Data
	 * @return
	 */
	public static String predict(TreeNode currentTreeNode, double[] Data) {
		while (currentTreeNode.getLeftNode() != null && currentTreeNode.getRightNode() != null) {
			if (Data[currentTreeNode.getAttr()] <= currentTreeNode.getSplitVal()) {
				currentTreeNode = currentTreeNode.getLeftNode();
			} else {
				currentTreeNode = currentTreeNode.getRightNode();
			}
		}
		return currentTreeNode.getLabel();
	}
	/**
	 * Print out entire Decision Tree Model
	 * @param queue
	 */
	public static void printDT(Vector<TreeNode> queue) {
		// print out decision tree
		while (queue.size() > 0) {
			TreeNode currentTreeNode = new TreeNode();
			currentTreeNode = queue.get(0);
			if (currentTreeNode.getLeftNode() != null || currentTreeNode.getRightNode() != null) {
				// add new node to backup queue
				// using the size of queue to locate branches
				int[] tempCount1 = new int[1];
				tempCount1[0] = 1;
				queueList.add(tempCount1);
				System.out.println(depth + ". If " + NumToAttr(currentTreeNode.getAttr()) + " <= " + currentTreeNode.getSplitVal() + ", goto "
						+ (queueList.size()) + " ,else goto " + (queueList.size() + 1));
				int[] tempCount2 = new int[1];
				tempCount2[0] = 1;
				queueList.add(tempCount2);
				// add new node to queue
				// remove old node
				TreeNode nLeft = new TreeNode();
				TreeNode nRight = new TreeNode();
				nLeft = currentTreeNode.getLeftNode();
				nRight = currentTreeNode.getRightNode();
				queue.add(nLeft);
				queue.add(nRight);
				queue.remove(0);
				depth++;
			} else if (currentTreeNode.getLeftNode() == null && currentTreeNode.getRightNode() == null) {
				// when approached leaf
				System.out.println(depth + ". Return rating " + currentTreeNode.getLabel());
				queue.remove(0);
				depth++;
			}
			printDT(queue);
		}
	}
	/**
	 * Convert Number to String Label
	 * @param i
	 * @return
	 */
	public static String NumToAttr(int i) {
		String Result = "UNF";
		if (i == 0) {
			Result = "WC TA";
		} else if (i == 1) {
			Result = "RE_TA";
		} else if (i == 2) {
			Result = "EBIT_TA";
		} else if (i == 3) {
			Result = "MVE_BVTD";
		} else if (i == 4) {
			Result = "S_TA";
		}
		return Result;
	}
	/**
	 * Round double decimal number to four
	 * @param d
	 * @param decimalPlace
	 * @return
	 */
	public static double round(double d, int decimalPlace) {
		// this function is going to round double decimal number to 4
		// decimal places
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
}