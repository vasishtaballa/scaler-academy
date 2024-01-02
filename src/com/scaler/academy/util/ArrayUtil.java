package com.scaler.academy.util;

import java.util.ArrayList;

public class ArrayUtil {
    public static ArrayList<ArrayList<Integer>> getArrayListFromMatrix(int[][] matrix) {
        ArrayList<ArrayList<Integer>> matrixList = new ArrayList<>();
        for (int i = 0; i < matrix[0].length; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix.length; j++) {
                row.add(matrix[i][j]);
            }
            matrixList.add(row);
        }
        return matrixList;
    }
}
