package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;

/**
 * Problem Description
 * <p>
 * Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)], if we split the array into some number of "chunks" (partitions), and individually sort each chunk. After concatenating them in order of splitting, the result equals the sorted array.
 * <p>
 * What is the most number of chunks we could have made?
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 100000
 * 0 <= A[i] < N
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return the maximum number of chunks that we could have made.
 * <p>
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4, 0]
 * Input 2:
 * <p>
 * A = [2, 0, 1, 3]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * Output 2:
 * <p>
 * 2
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * A = [1, 2, 3, 4, 0]
 * To get the 0 in the first index, we have to take all elements in a single chunk.
 * Explanation 2:
 * <p>
 * A = [2, 0, 1, 3]
 * We can divide the array into 2 chunks.
 * First chunk is [2, 0, 1] and second chunk is [3].
 */
public class MaxChunksToMakeSorted {
    public static void main(String[] args) {
        MaxChunksToMakeSorted obj = new MaxChunksToMakeSorted();
        int[] array = {2, 0, 1, 3};
        System.out.println(obj.solve(ArrayUtil.getIntegerArrayListFromArray(array)));
    }

    /**
     * Calculates the maximum number of chunks that can be made from the array A such that sorting each chunk individually and concatenating them results in a sorted array.
     * Intuition:
     * The idea is to keep track of the sum of elements in the current chunk and compare it with the expected sum of the first 'i' natural numbers.
     * When the two sums match, it indicates that we can form a valid chunk.
     *
     * @param A Input array which is a permutation of [0, 1, 2, ..., N-1].
     * @return The maximum number of chunks that can be made.
     */
    public int solve(ArrayList<Integer> A) {
        // Initialize the count of maximum chunks
        int maxChunks = 0;
        // Variable to keep track of the sum of elements in the current chunk
        long currentSum = 0;
        // Iterate through the array
        for (int i = 0; i < A.size(); i++) {
            // Add the current element to the running sum
            currentSum += A.get(i);
            // Calculate the expected sum of numbers from 0 to i (inclusive)
            long expectedSumTillIndex = ((long) i * (i + 1)) / 2;
            // If the running sum matches the expected sum, we can form a chunk here
            if (currentSum == expectedSumTillIndex) {
                maxChunks++;
            }
        }
        // Return the total number of chunks found
        return maxChunks;
    }
}
