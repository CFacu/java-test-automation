package com.solvd.lambda;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LambdaSort bubble = new LambdaSort() {
            @Override
            public <T extends Comparable> void sort(List<T> list) {
                for (int i = 1; list.size() > i; i++) {
                    for (int j = 0; (list.size() - 1) > j; j++) {
                        if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                            T aux = list.get(j);
                            list.set(j, list.get(j+1));
                            list.set(j+1, aux);
                        }
                    }
                }
            }
        };

        ListToSort<String> sortList = new ListToSort<>(new String[]{"yy", "cc", "ll" ,"bb", "xx", "aa"});
        sortList.sort(bubble);
        System.out.println(sortList.list.toString());
    }
}