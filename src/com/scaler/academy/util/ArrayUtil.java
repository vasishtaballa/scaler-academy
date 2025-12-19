package com.scaler.academy.util;

import java.util.ArrayList;

public class ArrayUtil {
    public static ArrayList<ArrayList<Integer>> getArrayListFromMatrix(int[][] matrix) {
        ArrayList<ArrayList<Integer>> matrixList = new ArrayList<>();
        for (int[] ints : matrix) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix[0].length; j++) {
                row.add(ints[j]);
            }
            matrixList.add(row);
        }
        return matrixList;
    }

    public static ArrayList<Integer> getIntegerArrayListFromArray(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
        return list;
    }

    public static ArrayList<Long> getEmptyLongArrayListOfSize(int size) {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(0L);
        }
        return list;
    }
}

