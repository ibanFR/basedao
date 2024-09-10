package com.ibanfr.infrastructure.dao;

import java.util.Optional;

public interface Dao<T>{

    void save(T entity);

    Optional<T> findById(Long id);
}
