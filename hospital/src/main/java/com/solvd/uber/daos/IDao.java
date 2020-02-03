package com.solvd.uber.daos;

import java.util.Set;

public interface IDAO<T> {
    T get(Long id);
    Set<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(Long id);
}