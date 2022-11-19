package pgdp.megamerge;

import java.util.Arrays;

public class MegaMergeSort {

	/**
	 * Sorts the array using mega merge sort with div splits
	 * @param array the array to be sorted
	 * @param div the split factor
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div) {
		//megaMergeSort mit verschiedenen Arrays aus divs[][] ausführen
		return megaMergeSort(array, div, 0, array.length);
	}

	/**
	 * Sorts the array using mega merge sort with div splits in the defined range
	 * @param array the array to be sorted
	 * @param div the split factor
	 * @param from the lower bound (inclusive)
	 * @param to the upper bound (exclusive)
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div, int from, int to) {
		int[][] divs = new int[to - from][];
		int i = 0;
		//catch falls zwischen to und from kein Eintrag oder Array leer oder null
		if (to - from < 1 || array.length == 0 || array == null) {
			return new int[0];
		}

		int[] t = mMS_helper(array, div, from, to);
		System.out.println(Arrays.toString(t));
		//füge Werte von t in neues zweidimensionales Array ein falls Arrays Länge == 1
		return null;
	}

	protected int[] mMS_helper(int[] array, int div, int from, int to) {
		int[][] divs = new int[div][];
		//Array zu from bis to verändern
		int[] temparray = new int[to - from];
		int i_t = 0;
		for (int i = from; i < to; i++) {
			temparray[i_t] = array[i];
			i_t++;
		}
		array = temparray;

		//Stoppbedingung falls array Länge 0 oder 1
		if (array.length == 1) {
			return array;
		}

		int n = 0;
		//berechne Rest
		int rest = array.length % div;

		//falls eines der ersten Pakete und Rest noch nicht verteilt
		for (int r = 0; r < rest; r++) {
			divs[n] = new int[array.length / div + 1];
			n++;
		}
		//restliche Teilpakete haben Länge array.length / div
		for (int r = rest; r < div; r++) {
			divs[n] = new int[array.length / div];
			n++;
		}
		//System.out.println(Arrays.deepToString(divs));

		//arrays mit Werten auffüllen
		int count = 0;
		for (int i = 0; i < divs.length; i++) {
			for (int j = 0; j < divs[i].length; j++) {
				//falls größe des arrays == 1
				if (divs[i].length == 1) {
					divs[i][j] = array[count];
					count++;
				} else {
					if (i < rest) {
						divs[i][j] = array[count];
						count++;
					} else {
						divs[i][j] = array[count];
						count++;
					}
				}
			}

			//falls alle Arrays nur noch 1 lang und belegt
			/*if (count == divs.length - 1) {
				return merge(divs, 0, div);
			} else {
				megaMergeSort(divs[i], div, 0, divs[i].length);
			}*/
			megaMergeSort(divs[i], div, 0, divs[i].length);
		}
		//System.out.println(Arrays.toString(array));//
		//System.out.println(Arrays.deepToString(divs));
		//System.out.println(Arrays.toString(merge(divs, 0, div)));

		return null;
	}

	/**
	 * Merges all arrays in the given range
	 * @param arrays to be merged
	 * @param from lower bound (inclusive)
	 * @param to upper bound (exclusive)
	 * @return the merged array
	 */
	protected int[] merge(int[][] arrays, int from, int to) {
		return m_go(arrays, from, to, new int[0]);
	}

	protected int[] m_go(int[][] arrays, int from, int to, int[] m_merged) {
		if (to - 1 - from > 0) {
			//catch wenn to größer als die Anzahl von zu mergenden Arrays ist oder from ist negativ
			if (to > arrays.length || from < 0) {
				return new int[0];
			}
			m_merged = merge(arrays[to - 2], arrays[to - 1]);
			arrays[to - 2] = m_merged;
			return m_go(arrays, from, to - 1, m_merged);
		} else {
			return m_merged;
		}
	}

	/**
	 * Merges the given arrays into one
	 * @param arr1 the first array
	 * @param arr2 the second array
	 * @return the resulting array
	 */
	protected int[] merge(int[] arr1, int[] arr2) {
		//Code von merge() aus Präsenzaufgabe kopiert, da Johannes Stöhr auf Zulip bestätigt hat, dass man es nutzen darf

		int[] merged = new int[arr1.length + arr2.length];
		int nextPositionFirst = 0;
		int nextPositionSecond = 0;
		int nextPositionMerged = 0;

		while(!(nextPositionFirst >= arr1.length) && !(nextPositionSecond >= arr2.length)) {
			if(arr1[nextPositionFirst] < arr2[nextPositionSecond]) {
				merged[nextPositionMerged++] = arr1[nextPositionFirst++];
			} else {
				merged[nextPositionMerged++] = arr2[nextPositionSecond++];
			}
		}

		while(nextPositionFirst < arr1.length) {
			merged[nextPositionMerged++] = arr1[nextPositionFirst++];
		}
		while(nextPositionSecond < arr2.length) {
			merged[nextPositionMerged++] = arr2[nextPositionSecond++];
		}

		return merged;
	}

	public static void main(String[] args) {
		MegaMergeSort mms = new MegaMergeSort();
		int[] arr = new int[] { 1, 2, 6, 7, 4, 3, 8, 9, 0, 5};
		//int[] arr = new int[] { 1, 2};
		int[] res = mms.megaMergeSort(arr, 4);
		//System.out.println(Arrays.toString(res));

		//System.out.println(Arrays.toString(mms.merge(new int[][] {{7}, {4}, {3}, {}}, 0, 3)));

		//int[][] arrays = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {7, 8, 9}, {5, 6, 7}};
		//System.out.println(Arrays.toString(merge(arrays, -1, 4)));
	}
}
