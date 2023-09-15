package com.ibanfr.infrastructure.dao;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Transactional
@Interceptor
@Slf4j
public class TransactionInterceptor {

    private SessionFactory sessionFactory;

    @Inject
    public TransactionInterceptor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @AroundInvoke
    public Object doInTransaction(InvocationContext context) throws Exception {

        log.debug("*** Intercepted method ***");

        Object obj = null;

        Session currentSession = null;

        try {
            currentSession = sessionFactory.getCurrentSession();

            currentSession.beginTransaction();

            obj = context.proceed();

            currentSession.getTransaction().commit();

        } catch (Exception e) {

            if (currentSession !=null && currentSession.getTransaction().isActive())
                currentSession.getTransaction().rollback();

            throw new RuntimeException(e);
        }

        return obj;
    }
}
