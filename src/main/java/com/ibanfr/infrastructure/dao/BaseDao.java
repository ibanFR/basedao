package com.ibanfr.infrastructure.dao;


import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

@Dependent
public class BaseDao<T> {

    SessionFactory sessionFactory;

    @Inject
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }
}
