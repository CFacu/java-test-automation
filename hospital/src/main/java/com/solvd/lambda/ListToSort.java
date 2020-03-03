package com.solvd.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToSort<T extends Comparable> {
    public List<T> list;

    public ListToSort(T[] array) {
        list = new ArrayList<T>(Arrays.asList(array));
    }

    public void sort(LambdaSort lambdaSort) {
        lambdaSort.sort(list);
    }
}
