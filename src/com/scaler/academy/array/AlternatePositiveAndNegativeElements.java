package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;

/**
 * Problem Description
 * <p>
 * Given an array of integers A, arrange them in an alternate fashion such that every non-negative number is followed by negative and vice-versa, starting from a negative number, maintaining the order of appearance. The number of non-negative and negative numbers need not be equal.
 * <p>
 * <p>
 * If there are more non-negative numbers, they appear at the end of the array. If there are more negative numbers, they also appear at the array's end.
 * <p>
 * Note: Try solving with O(1) extra space.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 7000
 * -109 <= A[i] <= 109
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return the modified array.
 * <p>
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [-1, -2, -3, 4, 5]
 * Input 2:
 * <p>
 * A = [5, -17, -100, -11]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [-1, 4, -2, 5, -3]
 * Output 2:
 * <p>
 * [-17, 5, -100, -11]
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * A = [-1, -2, -3, 4, 5]
 * Move 4 in between -1 and -2, A => [-1, 4, -2, -3, 5]
 * Move 5 in between -2 and -3, A => [-1, 4, -2, 5, -3]
 */
public class AlternatePositiveAndNegativeElements {
    public static void main(String[] args) {
        AlternatePositiveAndNegativeElements obj = new AlternatePositiveAndNegativeElements();
        int[] array = {-1, -2, -3, 4, 5};
        ArrayList<Integer> result = obj.solve(ArrayUtil.getIntegerArrayListFromArray(array));
        System.out.println(result);
    }

    /**
     * Rearranges the array such that non-negative and negative numbers alternate, starting with a negative number.
     * Maintains the order of appearance and uses O(1) extra space.
     * Intuition:
     * We iterate through the array and identify indices where the current element does not match the expected
     * sign based on its position (even indices should have negative numbers and odd indices should have
     * positive numbers). When we find such an index, we look for the next element that can be swapped
     * to correct the order. We then perform a right rotation of the subarray to place the found element
     * in the correct position while maintaining the order of other elements.
     * <p>
     * How it works:
     * 1. Traverse the array and check each index:
     * - If the index is even and the element is non-negative, mark it as
     * invalid.
     * - If the index is odd and the element is negative, mark it as invalid.
     * 2. For each invalid index, search for the next element that can be swapped
     * to fix the order.
     * 3. Perform a right rotation of the subarray from the invalid index to the found
     * index to place the correct element in the invalid position.
     * 4. Continue this process until the entire array is traversed.
     *
     * @param A Input array of integers
     * @return Rearranged array with alternating non-negative and negative numbers
     */
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        int invalidIndex = -1;
        for (int i = 0; i < A.size(); i++) {
            if (i % 2 == 0 && A.get(i) >= 0) {
                // positive indices should contain only negative elements. 0 should be considered under positive number category
                invalidIndex = i;
            } else if (i % 2 != 0 && A.get(i) < 0) {
                // negative indices should contain only positive elements
                invalidIndex = i;
            }
            if (invalidIndex != -1) {
                // find the next element which can be swapped with the invalid index
                for (int j = invalidIndex + 1; j < A.size(); j++) {
                    if ((invalidIndex % 2 == 0 && A.get(j) < 0) || (invalidIndex % 2 != 0 && A.get(j) >= 0)) {
                        // invalid index is even, so we need to find a negative number to swap and found the negative number at index j
                        int valueToBeMoved = A.get(j);
                        // shift all elements between invalidIndex and j to right by 1 position
                        rightRotateArraySegment(A, invalidIndex, j);
                        invalidIndex = -1;
                        break;
                    }
                }
            }
        }
        return A;
    }

    private void rightRotateArraySegment(ArrayList<Integer> A, int start, int end) {
        int valueToBeMoved = A.get(end);
        for (int i = end; i > start; i--) {
            A.set(i, A.get(i - 1));
        }
        A.set(start, valueToBeMoved);
    }
}
