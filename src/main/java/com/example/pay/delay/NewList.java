package com.example.pay.delay;

import com.google.common.collect.HashBasedTable;

public class NewList {
    public static void main(String[] args) {
        HashBasedTable hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(1, 1, 1);
        hashBasedTable.put(1, 2, 2);
        hashBasedTable.put(1, 3, 3);
        hashBasedTable.put(1, 4, 4);
        hashBasedTable.put(1, 5, 5);
        hashBasedTable.put(2, 5, 5);
        System.out.println(hashBasedTable);
        System.out.println(hashBasedTable.column(2));
        System.out.println(hashBasedTable.columnKeySet());
        System.out.println(hashBasedTable.row(1));
        System.out.println(hashBasedTable.values());
        int[][] a = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }

    }
}
