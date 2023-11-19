package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Identity;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

import java.util.Optional;

@Dependent
public class BaseDao<T  extends Identity> implements Dao<T>{

    Class<T> type;

    SessionFactory sessionFactory;

    @Inject
    public BaseDao(Class<T> type, SessionFactory sessionFactory) {

        this.type = type;

        //TypeToken<T> typeToken = new TypeToken<>(getClass()) {};
        //this.type = (Class<T>) typeToken.getRawType();

        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    @Transactional
    public Optional<T> findById(Integer id) {

        return Optional.ofNullable(sessionFactory.getCurrentSession()
                                           .find(type,id));
    }
}
