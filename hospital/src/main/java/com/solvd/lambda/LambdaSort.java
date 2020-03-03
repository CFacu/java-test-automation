package com.solvd.lambda;

import java.util.List;

@FunctionalInterface
public interface LambdaSort {
    <T extends Comparable> void sort(List<T> list);
}
