package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Identity;

import java.util.Optional;

public interface Dao<T>{

    void save(T entity);

    Optional<T> findById(Integer id);
}
