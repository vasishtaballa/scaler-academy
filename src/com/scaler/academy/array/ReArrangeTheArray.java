package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array of integers A of size N that is a permutation of [0, 1, 2, …, (N-1)].
 * <p>
 * Rearrange the array such that A[i] = j is changed to A[j] = i for all i and j form 0 to N-1.
 * <p>
 * Note: Try solving this with O(1) extra space.
 * <p>
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * Output Format
 * <p>
 * Return the rearranged array A.
 * Constraints
 * <p>
 * 1 <= N <= 100000
 * 0 <= A[i] < N
 * For Example
 * <p>
 * Input 1:
 * A = [1, 2, 3, 4, 0]
 * Output 1:
 * [4, 0, 1, 2, 3]
 * <p>
 * Input 2:
 * A = [2, 0, 1, 3]
 * Output 2:
 * [1, 2, 0, 3]
 * Expected Output
 * Provide sample input and click run to see the correct output for the provided input. Use this to improve your problem understanding and test edge cases
 * Correct Output
 * [2,0,4,3,1]
 * 3, 2, 0, 1
 * 0
 */
public class ReArrangeTheArray {

    public static void main(String[] args) {
        ReArrangeTheArray obj = new ReArrangeTheArray();
        int[] array = {1, 2, 3, 4, 0};
        ArrayList<Integer> result = obj.solve(ArrayUtil.getIntegerArrayListFromArray(array));
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * Rearranges the array such that A[i] = j is changed to A[j] = i for all i and j from 0 to N-1.
     * <p>
     * This method uses cycle detection and in-place modification to rearrange the array without using extra space.
     * It marks visited indices by storing negative values, allowing it to track cycles and rearrange elements accordingly.
     * </p>
     * The idea is to traverse the array and follow the cycles formed by the indices and values.
     * For each unvisited index, we follow the cycle and rearrange the elements by placing each index at its corresponding value's position.
     * We mark visited indices by storing negative values (specifically, -(index + 1)) to avoid confusion with valid indices.
     * After processing all indices, we restore the original values by negating them back to positive.
     * <p>
     * Intuition:
     * - Each element in the array points to another index, forming cycles.
     * - By following these cycles, we can rearrange the elements in-place.
     * - Marking visited indices helps to avoid processing the same cycle multiple times.
     * <p>
     * Idea behind storing -(i + 1):
     * - We use negative values to mark visited indices.
     * - Storing -(i + 1) ensures that we can distinguish between visited and unvisited indices.
     * - The +1 is added to avoid confusion with index 0, which would be stored as 0 if we simply negated it.
     * - If we don't add +1, we wouldn't be able to differentiate between an index that was originally 0 and one that was visited and marked as 0.
     *
     * @param A
     * @return
     */
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        int N = A.size();
        for (int i = 0; i < N; i++) {
            int currentValue = A.get(i);
            if (currentValue >= 0) {
                int nextValue = A.get(currentValue);
                while (nextValue >= 0) {
                    A.set(currentValue, -(i + 1)); // Store the index as negative to mark it visited
                    i = currentValue;
                    currentValue = nextValue;
                    nextValue = A.get(currentValue);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            A.set(i, -(A.get(i) + 1)); // Restore the original indices
        }
        return A;
    }

    /**
     * Rearranges the array such that A[i] = j is changed to A[j] = i for all i and j from 0 to N-1.
     * <p>
     * This method uses an encoding technique to store both old and new values in the same array
     * without using extra space.
     * The encoding is done by leveraging the fact that all values are in the range [0, N-1].
     * Algorithm to encode the values:
     * 1. For each index i, calculate the new value to be placed at index A[i] using the formula:
     * newValue = oldValue + (i * N)
     * 2. Update the value at index A[i] with this newValue.
     * 3. After processing all indices, decode the values by dividing each element by N
     * to retrieve the new values.
     * How it works:
     * - The old value is preserved using modulo operation (A.get(i) % N). This gives the original value at index i.
     * - The new value is calculated by adding (i * N) to the old value. This ensures that the new value is distinct
     * and can be retrieved later.
     * - Finally, dividing each element by N retrieves the new values since the old values are less than N.
     * - Dividing by N effectively shifts out the old values because the oldValue is always less than N and integer division with N makes that 0 leaving (i * N) / N => i.
     * But this approach doesn't work if N*N exceeds the integer limit. Hence, this approach will not work for very large values of N.
     *
     * @param A
     * @return
     */
    public ArrayList<Integer> solve2(ArrayList<Integer> A) {
        int N = A.size();

        // Step 1: Encode both old and new values using long
        for (int i = 0; i < N; i++) {
            int value = (int) (A.get(i) % N);  // get original value
            long updatedValue = A.get(value) + (long) i * N;
            A.set(value, (int) updatedValue);
        }

        // Step 2: Decode to get final values
        for (int i = 0; i < N; i++) {
            A.set(i, A.get(i) / N);
        }

        return A;
    }

    /**
     * Rearranges the array such that A[i] = j is changed to A[j] = i for all i and j from 0 to N-1.
     * <p>
     * The method creates a new array where each element's index is placed at the position of its value in the original array.
     *
     * @param A the input array as an ArrayList of integers
     * @return the rearranged array as an ArrayList of integers
     */
    public ArrayList<Integer> solve1(ArrayList<Integer> A) {
        Integer[] array = new Integer[A.size()];
        for (int i = 0; i < A.size(); i++) {
            array[A.get(i)] = i;
        }
        return new ArrayList<>(Arrays.asList(array));
    }

}
