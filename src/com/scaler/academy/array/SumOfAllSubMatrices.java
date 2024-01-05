package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;


/**
 * Problem Description
 * Given a 2D Matrix A of dimensions N*N, we need to return the sum of all possible submatrices.
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= N <=30
 * <p>
 * 0 <= A[i][j] <= 10
 * <p>
 * <p>
 * <p>
 * Input Format
 * Single argument representing a 2-D array A of size N x N.
 * <p>
 * <p>
 * <p>
 * Output Format
 * Return an integer denoting the sum of all possible submatrices in the given matrix.
 * <p>
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * A = [ [1, 1]
 * [1, 1] ]
 * Input 2:
 * A = [ [1, 2]
 * [3, 4] ]
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * 16
 * Output 2:
 * 40
 * <p>
 * <p>
 * Example Explanation
 * Example 1:
 * Number of submatrices with 1 elements = 4, so sum of all such submatrices = 4 * 1 = 4
 * Number of submatrices with 2 elements = 4, so sum of all such submatrices = 4 * 2 = 8
 * Number of submatrices with 3 elements = 0
 * Number of submatrices with 4 elements = 1, so sum of such submatrix = 4
 * Total Sum = 4+8+4 = 16
 * Example 2:
 * The submatrices are [1], [2], [3], [4], [1, 2], [3, 4], [1, 3], [2, 4] and [[1, 2], [3, 4]].
 * Total sum = 40
 */
public class SumOfAllSubMatrices {
    public static void main(String[] args) {
        SumOfAllSubMatrices obj = new SumOfAllSubMatrices();
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        System.out.println(obj.solve(ArrayUtil.getArrayListFromMatrix(matrix)));
    }

    /**
     * Bruteforce is to find all the possible sub matrices and sum up all of them and give the result
     * The optimised solution is to find out
     * For every element - How many sub matrices will contain this element
     * Let's say element at [i][j] is part of 4 sub matrices, then this element at [i][j] will contribute the value (4 * a[i][j]) to the total sum
     * Now, we have to find out how many sub matrices will it be part of for every element
     * Before we go there, let's have another intuition
     * <p>
     * Every sub matrix in a matrix will be identified by two points [TopLeft and BottomRight]. With these two points - we can tell what all elements could be part of that sub matrix
     * </p>
     * <p>
     * Let's say there is an element at [i][j] - Imagine that this element is somewhere around the center of the matrix
     * There could be x no of topLeft points and y no of bottomRight points for this element at [i][j] which forms a lot of sub-matrices
     * Any other sub matrices which are formed by the other topLeft and bottomRight points will not contain the element at [i][j]
     * </p>
     * <p>
     * How do we find how many topLeft and bottomRight points will be there for a given element at [i][j]?
     * If you imagine the element is somewhere around the center of the matrix, there would be
     * (i + 1) * (j + 1) topLeft points
     * and
     * (n - i) * (n - j) bottomRight points
     * </p>
     * <p>
     * The no of sub matrices that could be created with the current element will be no of topLeft points * no of bottomRight points.
     * This is because, those many sub matrices could be formed with all those points
     * </p>
     * <p>
     * Now the current element's contribution would be
     * a[i][j] * (no of sub matrices that this element could be part of)
     * a[i][j] * (no of topLeft points * no of bottomRight points)
     * a[i][j] * (((i + 1) * (j + 1)) * ((n - i) * (n - j)))
     * </p>
     * <p>
     * The above contribution is for one element. Similarly, we should do this for all the elements in the matrix and sum up to the result
     * </p>
     *
     * @param A - Matrix
     * @return sum of all sub matrices
     */
    public int solve(ArrayList<ArrayList<Integer>> A) {
        int size = A.size();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int element = A.get(i).get(j);
                int noOfTopLeftCells = (i + 1) * (j + 1);
                int noOfBottomRightCells = (size - i) * (size - j);
                int contribution = element * noOfTopLeftCells * noOfBottomRightCells;
                sum += contribution;
            }
        }
        return sum;
    }
}
