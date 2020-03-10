package com.solvd.uber.daos.interfaces;

import java.util.List;

public interface IDAO<T> {
    T get(Long id);
    List<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(Long id);
}