package com.scaler.academy.array;

import com.scaler.academy.util.ArrayUtil;

import java.util.ArrayList;

/**
 * Problem Description
 * Given a matrix of integers A of size N x M and an integer B.
 * In the given matrix every row and column is sorted in non-decreasing order. Find and return the position of B in the matrix in the given form:
 * If A[i][j] = B then return (i * 1009 + j)
 * If B is not present return -1.
 * <p>
 * Note 1: Rows are numbered from top to bottom and columns are numbered from left to right.
 * Note 2: If there are multiple B in A then return the smallest value of i*1009 +j such that A[i][j]=B.
 * Note 3: Expected time complexity is linear
 * Note 4: Use 1-based indexing
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= N, M <= 1000
 * -100000 <= A[i] <= 100000
 * -100000 <= B <= 100000
 * <p>
 * <p>
 * Input Format
 * The first argument given is the integer matrix A.
 * The second argument given is the integer B.
 * <p>
 * <p>
 * Output Format
 * Return the position of B and if it is not present in A return -1 instead.
 * <p>
 * <p>
 * Example Input
 * Input 1:-
 * A = [[1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]]
 * B = 2
 * Input 2:-
 * A = [[1, 2]
 * [3, 3]]
 * B = 3
 * <p>
 * <p>
 * Example Output
 * Output 1:-
 * 1011
 * Output 2:-
 * 2019
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:-
 * A[1][2] = 2
 * 1 * 1009 + 2 = 1011
 * Explanation 2:-
 * A[2][1] = 3
 * 2 * 1009 + 1 = 2019
 * A[2][2] = 3
 * 2 * 1009 + 2 = 2020
 * The minimum value is 2019
 */
public class SearchInARowWiseColumnWiseSortedMatrix {

    public static void main(String[] args) {
        SearchInARowWiseColumnWiseSortedMatrix obj = new SearchInARowWiseColumnWiseSortedMatrix();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        ArrayList<ArrayList<Integer>> list = ArrayUtil.getArrayListFromMatrix(matrix);
        System.out.println(obj.solve(list, 2));
    }

    /**
     * We need to leverage the row wise and column wise sorting nature in the input
     * If you observe all the corner elements, top right and bottom left corner elements are little tricky
     * All the elements above/below them or All the elements left/right to them are either smaller or greater.
     * We need to use this advantage and try to solve the problem
     * Let's start with the top right corner and
     * if the currentElement is smaller than target, you should go and search in the next row (as all numbers below that would be greater and left to it would be smaller)
     * if the currentElement is greater than target, you should go and search in the previous column (as all numbers below that would be greater and left to it would be smaller)
     * if the currentElement is equal to the target, then we should find the left most occurrence of the same element in the same row (that would give the smallest value for i * 1009 + j)
     *
     * @param A - Matrix
     * @param B - Target element to search
     * @return - i * 1009 + j
     */
    public int solve(ArrayList<ArrayList<Integer>> A, int B) {
        if (A.isEmpty() || A.get(0).isEmpty()) return -1;
        int rows = A.size(), cols = A.get(0).size();
        int i = 0, j = cols - 1; // to point to the top right element in the matrix
        boolean found = false;
        while (i < rows && j >= 0) {
            int element = A.get(i).get(j);
            if (element < B) {
                i += 1;
            } else if (element > B) {
                j -= 1;
            } else {
                // find the left most occurrence of the same element in the same row
                found = true;
                int index = 0;
                while (index < j) {
                    if (A.get(i).get(index) == B)
                        return (i + 1) * 1009 + index + 1;
                    index += 1;
                }
                break;
            }
        }
        return found ? (i + 1) * 1009 + j + 1 : -1;
    }
}
