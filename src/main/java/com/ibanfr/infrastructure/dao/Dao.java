package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Identity;

import java.util.Optional;

public interface Dao<T extends Identity >{

    void save(T entity);

    Optional<T> findById(Integer id);
}
