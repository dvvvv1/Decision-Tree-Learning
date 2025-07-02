/**
 * This is the base class of the Tree Data Sort Algorithm.
 * @author pz.yao
 */

package algorithms;

import java.util.Vector;

public class SortTreeData {
	/**
	 * This function uses shell sort to sort the data value
	 * @param Data
	 * @param index
	 * @return
	 */
	public static Vector<double[]> sortData(Vector<double[]> Data, int index) {
		// Setup container
		int inner, outer;
		// Find initial value of h
		int h = 1;
		while (h <= Data.size() / 3) {
			h = h * 3 + 1;
		}

		while (h > 0) {
			for (outer = h; outer < Data.size(); outer++) {
				double[] temp = new double[6];
				temp[0] = Data.get(outer)[0];
				temp[1] = Data.get(outer)[1];
				temp[2] = Data.get(outer)[2];
				temp[3] = Data.get(outer)[3];
				temp[4] = Data.get(outer)[4];
				temp[5] = Data.get(outer)[5];
				inner = outer;
				while (inner > h - 1 && Data.get(inner - h)[index] > temp[index]) {
					Data.get(inner)[0] = Data.get(inner - h)[0];
					Data.get(inner)[1] = Data.get(inner - h)[1];
					Data.get(inner)[2] = Data.get(inner - h)[2];
					Data.get(inner)[3] = Data.get(inner - h)[3];
					Data.get(inner)[4] = Data.get(inner - h)[4];
					Data.get(inner)[5] = Data.get(inner - h)[5];
					inner -= h;
				}
				Data.get(inner)[0] = temp[0];
				Data.get(inner)[1] = temp[1];
				Data.get(inner)[2] = temp[2];
				Data.get(inner)[3] = temp[3];
				Data.get(inner)[4] = temp[4];
				Data.get(inner)[5] = temp[5];
			}
			h = (h - 1) / 3; // decrease h
		}
		return Data;
	}
}
