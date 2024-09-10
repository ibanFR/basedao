package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.EntityId;

import java.util.*;

public class InMemoryDao<T extends EntityId> implements Dao<T>{

    List<T> repository = new ArrayList<>();

    @Override
    public void save(T entity) {
        entity.setId(new Random().nextLong());
        repository.add(entity);
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.stream()
                .filter(t -> t.getId()
                        .equals(id))
                .findFirst();
    }
}
