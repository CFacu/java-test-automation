package com.solvd.uber.daos;

import com.solvd.uber.models.User;

import java.util.Optional;
import java.util.Set;

public interface Dao<T> {
    Optional<T> get(Long id);
    Set<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(Long id);
}
