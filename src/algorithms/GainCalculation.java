package algorithms;

import java.util.Vector;
/**
 * This is the base class of gain value calculation.
 * @author puzhiyao
 *
 */
public class GainCalculation {
	/**
	 * This functions calculate the Information Gain Value.
	 * @param sortedData
	 * @param attrNumber
	 * @param tempSplitVal
	 * @return
	 */
	public static double calGain(Vector<double[]> sortedData, int attrNumber, double tempSplitVal) {
		double infoCont = 0;
		// calculate entropy
		double[] Ncount = new double[8];// number of
										// AAA,AA,A,BBB,BB,B,CCC,unknown
		// initialize
		for (int i = 0; i < Ncount.length; i++) {
			Ncount[i] = 0;
		}
		// count number of each label appeared in data
		for (int i = 0; i < sortedData.size(); i++) {
			if (sortedData.get(i)[5] == 1) {
				Ncount[1]++;
			} else if (sortedData.get(i)[5] == 2) {
				Ncount[2]++;
			} else if (sortedData.get(i)[5] == 3) {
				Ncount[3]++;
			} else if (sortedData.get(i)[5] == 4) {
				Ncount[4]++;
			} else if (sortedData.get(i)[5] == 5) {
				Ncount[5]++;
			} else if (sortedData.get(i)[5] == 6) {
				Ncount[6]++;
			} else if (sortedData.get(i)[5] == 7) {
				Ncount[7]++;
			} else {
				Ncount[0]++;
			}
		}
		double[] Pc = new double[8];
		double totSamples = sortedData.size();
		for (int i = 0; i < Pc.length; i++) {
			Pc[i] = Ncount[i] / totSamples;
		}
		// calculate information content
		for (int i = 0; i < Pc.length; i++) {
			double tempR;
			if (Pc[i] == 0) {
				tempR = 0;
			} else {
				tempR = Math.log(Pc[i]) / Math.log(2);
			}
			infoCont = infoCont - Pc[i] * tempR;
		}

		// calculate entropy left
		double[] NcountLeft = new double[8];
		double totLeftSamples = 0;
		for (int i = 0; i < NcountLeft.length; i++) {
			NcountLeft[i] = 0;
		}
		for (int i = 0; i < sortedData.size(); i++) {
			if (sortedData.get(i)[attrNumber] <= tempSplitVal) {
				if (sortedData.get(i)[5] == 1) {
					NcountLeft[1]++;
				} else if (sortedData.get(i)[5] == 2) {
					NcountLeft[2]++;
				} else if (sortedData.get(i)[5] == 3) {
					NcountLeft[3]++;
				} else if (sortedData.get(i)[5] == 4) {
					NcountLeft[4]++;
				} else if (sortedData.get(i)[5] == 5) {
					NcountLeft[5]++;
				} else if (sortedData.get(i)[5] == 6) {
					NcountLeft[6]++;
				} else if (sortedData.get(i)[5] == 7) {
					NcountLeft[7]++;
				} else {
					NcountLeft[0]++;
				}
				totLeftSamples++;
			}
		}
		double[] PcLeft = new double[8];
		for (int i = 0; i < PcLeft.length; i++) {
			PcLeft[i] = NcountLeft[i] / totLeftSamples;
		}
		// calculate information content Left
		double infoContLeft = 0;
		for (int i = 0; i < PcLeft.length; i++) {
			double tempRL;
			if (PcLeft[i] == 0) {
				tempRL = 0;
			} else {
				tempRL = Math.log(PcLeft[i]) / Math.log(2);
			}
			infoContLeft = infoContLeft - PcLeft[i] * tempRL;
		}

		// calculate entropy right
		double[] NcountRight = new double[8];
		double totRightSamples = 0;
		for (int i = 0; i < NcountRight.length; i++) {
			NcountRight[i] = 0;
		}
		for (int i = 0; i < sortedData.size(); i++) {
			if (sortedData.get(i)[attrNumber] > tempSplitVal) {
				if (sortedData.get(i)[5] == 1) {
					NcountRight[1]++;
				} else if (sortedData.get(i)[5] == 2) {
					NcountRight[2]++;
				} else if (sortedData.get(i)[5] == 3) {
					NcountRight[3]++;
				} else if (sortedData.get(i)[5] == 4) {
					NcountRight[4]++;
				} else if (sortedData.get(i)[5] == 5) {
					NcountRight[5]++;
				} else if (sortedData.get(i)[5] == 6) {
					NcountRight[6]++;
				} else if (sortedData.get(i)[5] == 7) {
					NcountRight[7]++;
				} else {
					NcountRight[0]++;
				}
				totRightSamples++;
			}
		}
		double[] PcRight = new double[8];
		for (int i = 0; i < PcRight.length; i++) {
			PcRight[i] = NcountRight[i] / totRightSamples;
		}
		// calculate information content Right
		double infoContRight = 0;
		for (int i = 0; i < PcRight.length; i++) {
			double tempRR;
			if (PcRight[i] == 0) {
				tempRR = 0;
			} else {
				tempRR = Math.log(PcRight[i]) / Math.log(2);
			}
			infoContRight = infoContRight - PcRight[i] * tempRR;
		}

		// calculate information gain
		// information gain = Entropy_before - Entropy_after
		double EntropyAfter = (totLeftSamples / totSamples) * infoContLeft
				+ (totRightSamples / totSamples) * infoContRight;

		double information_Gain = infoCont - EntropyAfter;

		return information_Gain;
	}
}
