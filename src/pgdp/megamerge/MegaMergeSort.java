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
		// TODO
		return null;
	}

	/**
	 * Merges all arrays in the given range
	 * @param arrays to be merged
	 * @param from lower bound (inclusive)
	 * @param to upper bound (exclusive)
	 * @return the merged array
	 */
	public static int[] merge(int[][] arrays, int from, int to) {
		return m_go(arrays, from, to, new int[0]);
	}

	public static int[] m_go(int[][] arrays, int from, int to, int[] m_merged) {
		int[] merge2;
		if (to - 1 - from > 0) {
			//return merge(arrays[to - 2], merge(arrays, to - 2, to - 1));
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
	public static int[] merge(int[] arr1, int[] arr2) {
		/*int im = 0;
		int i2 = 0;
		int[] merge12 = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length + arr2.length; i++) {
			if (i2 == arr2.length - 1) {
				merge12[i] = arr1[i];
			} else if (arr1[i] <= arr2[i2]) {
				merge12[i] = arr1[i];
			} else {
				merge12[i] = arr2[i2];
				i2++;
			}
			im++;
		}
		return merge12;*/

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
		/*MegaMergeSort mms = new MegaMergeSort();
		int[] arr = new int[] { 1, 2, 6, 7, 4, 3, 8, 9, 0, 5 };
		int[] res = mms.megaMergeSort(arr, 4);
		System.out.println(Arrays.toString(res));*/
		int[][] arrays = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {7, 8, 9}, {5, 6, 7}};
		System.out.println(Arrays.toString(merge(arrays, 0, 4)));
	}
}
