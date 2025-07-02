/**
 * This is the node class of the decision tree model
 * @author pz.yao
 */

package utils;

public class TreeNode {

	/*
	 * Attributes of the node:
	 * WC_TA, RE_TA, EBIT_TA, MVE_BVTD, S_TA
	 */
	private int attr;
	/*
	 * Split value of the node
	 */
	private double splitval;
	/*
	 * Label of the node
	 */
	private String label;
	/*
	 * Tree node branch left
	 */
	private TreeNode left;
	/*
	 * Tree node branch right
	 */
	private TreeNode right;
	/*
	 * Initialize
	 */
	public TreeNode() {
		//this.label = "unknown";
		//this.left = null;
		//this.right = null;
	}
	/**
	 * This function sets attributes of the node
	 * @param attr
	 */
	public void setAttr(int attr) {
		// setup attribute value
		this.attr = attr;
	}
	/**
	 * This function sets the split value of the node
	 * @param splitval
	 */
	public void setSplitval(double splitval) {
		// setup split value
		this.splitval = splitval;
	}
	/**
	 * This function sets the label of the node
	 * @param label
	 */
	public void setLabel(double label) {
		// setup label
		if (label == 1) {
			this.label = "AAA";
		} else if (label == 2) {
			this.label = "AA";
		} else if (label == 3) {
			this.label = "A";
		} else if (label == 4) {
			this.label = "BBB";
		} else if (label == 5) {
			this.label = "BB";
		} else if (label == 6) {
			this.label = "B";
		} else if (label == 7) {
			this.label = "CCC";
		} else {
			this.label = "unknown";
		}
	}
	/**
	 * This function sets the label of the node
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * This function sets the child left node
	 * @param left
	 */
	public void setLeftNode(TreeNode left) {
		// setup left node
		this.left = left;
	}
	/**
	 * This function sets the child right node
	 * @param right
	 */
	public void setRightNode(TreeNode right) {
		// setup right node
		this.right = right;
	}
	/**
	 * This function returns attributes of the node
	 * @return
	 */
	public int getAttr() {
		// get attribute value
		return this.attr;
	}
	/**
	 * This function returns split value of the node
	 * @return
	 */
	public double getSplitVal() {
		// get split value
		return this.splitval;
	}
	/**
	 * This function returns label of the node
	 * @return
	 */
	public String getLabel() {
		// get label
		return this.label;
	}
	/**
	 * This function returns left child node of the node
	 * @return
	 */
	public TreeNode getLeftNode() {
		// get left node
		return this.left;
	}
	/**
	 * This function returns right child node of the node
	 * @return
	 */
	public TreeNode getRightNode() {
		// get right node
		return this.right;
	}

}
