package com.solvd.hospital.lambda;

import java.util.List;

@FunctionalInterface
public interface Sort<T> {
    <T extends Comparable> void sort(List<T> list);
}
