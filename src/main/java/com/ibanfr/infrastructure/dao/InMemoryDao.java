package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Identity;

import java.util.*;

public class InMemoryDao<T extends Identity> implements Dao<T>{

    List<T> repository = new ArrayList<>();

    @Override
    public void save(T entity) {
        entity.setId(new Random().nextInt());
        repository.add(entity);
    }

    @Override
    public Optional<T> findById(Integer id) {
        return repository.stream()
                .filter(t -> t.getId()
                        .equals(id))
                .findFirst();
    }
}
