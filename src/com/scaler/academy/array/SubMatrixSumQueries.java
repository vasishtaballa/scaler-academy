package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;

/**
 * Problem Description
 * <p>
 * Given a matrix of integers A of size N x M and multiple queries Q, for each query, find and return the submatrix sum.
 * <p>
 * <p>
 * <p>
 * <p>
 * Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.
 * <p>
 * NOTE:
 * <p>
 * Rows are numbered from top to bottom, and columns are numbered from left to right.
 * The sum may be large, so return the answer mod 109 + 7.
 * Also, select the data type carefully, if you want to store the addition of some elements.
 * Indexing given in B, C, D, and E arrays is 1-based.
 * Top Left 0-based index = (B[i] - 1, C[i] - 1)
 * Bottom Right 0-based index = (D[i] - 1, E[i] - 1)
 * <p>
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, M <= 1000
 * -100000 <= A[i] <= 100000
 * 1 <= Q <= 100000
 * 1 <= B[i] <= D[i] <= N
 * 1 <= C[i] <= E[i] <= M
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer matrix A.
 * The second argument given is the integer array B.
 * The third argument given is the integer array C.
 * The fourth argument given is the integer array D.
 * The fifth argument given is the integer array E.
 * (B[i], C[i]) represents the top left corner of the i'th query.
 * (D[i], E[i]) represents the bottom right corner of the i'th query.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return an integer array containing the submatrix sum for each query.
 * <p>
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [   [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]   ]
 * B = [1, 2]
 * C = [1, 2]
 * D = [2, 3]
 * E = [2, 3]
 * Input 2:
 * <p>
 * A = [   [5, 17, 100, 11]
 * [0, 0,  2,   8]    ]
 * B = [1, 1]
 * C = [1, 4]
 * D = [2, 2]
 * E = [2, 4]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [12, 28]
 * Output 2:
 * <p>
 * [22, 19]
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * For query 1: Submatrix contains elements: 1, 2, 4 and 5. So, their sum is 12.
 * For query 2: Submatrix contains elements: 5, 6, 8 and 9. So, their sum is 28.
 * Explanation 2:
 * <p>
 * For query 1: Submatrix contains elements: 5, 17, 0 and 0. So, their sum is 22.
 * For query 2: Submatrix contains elements: 11 and 8. So, their sum is 19.
 */
public class SubMatrixSumQueries {
    private static final Integer MOD = 1000000007;

    public static void main(String[] args) {
        SubMatrixSumQueries obj = new SubMatrixSumQueries();
        int[][] matrix = {
                {5, 17, 100, 11},
                {0, 0, 2, 8}
        };
        int[] topLeftIndexRowPos = {1, 1};
        int[] topLeftIndexColPos = {1, 4};
        int[] bottomRightIndexRowPos = {2, 2};
        int[] bottomRightIndexColPos = {2, 4};
        ArrayList<Integer> result = obj.solve(ArrayUtil.getArrayListFromMatrix(matrix),
                ArrayUtil.getIntegerArrayListFromArray(topLeftIndexRowPos),
                ArrayUtil.getIntegerArrayListFromArray(topLeftIndexColPos),
                ArrayUtil.getIntegerArrayListFromArray(bottomRightIndexRowPos),
                ArrayUtil.getIntegerArrayListFromArray(bottomRightIndexColPos));
        System.out.println(result);
    }

    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E) {
        ArrayList<ArrayList<Long>> prefixSum = getPrefixSum(A);
        ArrayList<Integer> result = new ArrayList<>();
        // solve for all the given queries using the prefix sum matrix
        for (int i = 0; i < B.size(); i++) {
            int topLeftRow = B.get(i) - 1;
            int topLeftCol = C.get(i) - 1;
            int bottomRightRow = D.get(i) - 1;
            int bottomRightCol = E.get(i) - 1;

            long subMatrixSum = prefixSum.get(bottomRightRow).get(bottomRightCol);
            if (topLeftRow > 0) {
                // remove the area above the submatrix
                subMatrixSum = subMatrixSum - prefixSum.get(topLeftRow - 1).get(bottomRightCol);
            }
            if (topLeftCol > 0) {
                // remove the area left to the submatrix
                subMatrixSum = subMatrixSum - prefixSum.get(bottomRightRow).get(topLeftCol - 1);
            }
            if (topLeftRow > 0 && topLeftCol > 0) {
                // add the overlapping area which is subtracted twice
                subMatrixSum = subMatrixSum + prefixSum.get(topLeftRow - 1).get(topLeftCol - 1);
            }
            subMatrixSum = ((subMatrixSum % MOD) + MOD) % MOD;
            result.add((int) subMatrixSum);
        }
        return result;
    }

    /**
     * Calculates the prefix sum matrix for the given matrix A.
     * <p>
     * The prefix sum at any position (i, j) in the matrix is the sum of all elements from the top-left corner (0, 0) to (i, j).
     * How to calculate:
     * If both i and j are greater than 0:
     * prefixSum[i][j] = A[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1]
     * If only i is greater than 0:
     * prefixSum[i][j] = A[i][j] + prefixSum[i-1][j]
     * If only j is greater than 0:
     * prefixSum[i][j] = A[i][j] + prefixSum[i][j-1]
     * If both i and j are 0:
     * prefixSum[i][j] = A[i][j]
     * How does it work:
     * The method iterates through each element of the matrix A and computes the prefix sum based on the above conditions.
     * It uses previously computed prefix sums to efficiently calculate the current prefix sum.
     * </p>
     *
     * @param A The input matrix for which the prefix sum matrix is to be calculated.
     * @return The prefix sum matrix.
     */
    private ArrayList<ArrayList<Long>> getPrefixSum(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<Long>> prefixSum = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            prefixSum.add(ArrayUtil.getEmptyLongArrayListOfSize(A.get(0).size()));
            for (int j = 0; j < A.get(0).size(); j++) {
                if (i > 0 && j > 0) {
                    // if both i and j are greater than 0, then the sum should be calculated by adding the current element,
                    // the prefix sum of the previous row and the prefix sum of the previous column, and subtracting the prefix sum of the previous row and previous column (to avoid double counting)
                    long sum = A.get(i).get(j) + prefixSum.get(i - 1).get(j) + prefixSum.get(i).get(j - 1) - prefixSum.get(i - 1).get(j - 1);
                    prefixSum.get(i).set(j, sum);
                } else if (i > 0) {
                    // if only i is greater than 0, then the sum should be calculated by adding the current element and the prefix sum of the previous row
                    long sum = A.get(i).get(j) + prefixSum.get(i - 1).get(j);
                    prefixSum.get(i).set(j, sum);
                } else if (j > 0) {
                    // if only j is greater than 0, then the sum should be calculated by adding the current element and the prefix sum of the previous column
                    long sum = A.get(i).get(j) + prefixSum.get(i).get(j - 1);
                    prefixSum.get(i).set(j, sum);
                } else {
                    prefixSum.get(i).set(j, (long) A.get(i).get(j));
                }
            }
        }
        return prefixSum;
    }
}
