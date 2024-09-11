package com.ibanfr.domain.model;

import java.util.Optional;

public interface Repository<T>{

    void save(T entity);

    Optional<T> findById(Long id);
}
