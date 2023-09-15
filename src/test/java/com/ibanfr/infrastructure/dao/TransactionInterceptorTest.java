package com.ibanfr.infrastructure.dao;

import jakarta.interceptor.InvocationContext;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TransactionInterceptorTest {

    @Mock
    InvocationContext invocationContext;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    SessionFactory sessionFactory;

    @InjectMocks
    TransactionInterceptor transactionInterceptor;

    @Test
    @DisplayName("should create new instance")
    void shouldCreateNewInstance() {

        //then
        assertThat(transactionInterceptor).isNotNull();
    }


    @Test
    @DisplayName("should get current session from session factory")
    void shouldGetCurrentSessionFromSessionFactory() throws Exception {

        //when
        transactionInterceptor.doInTransaction(invocationContext);

        //then
        then(sessionFactory).should().getCurrentSession();
    }

    @Test
    @DisplayName("should begin transaction")
    void shouldBeginTransaction() throws Exception {

        //when
        transactionInterceptor.doInTransaction(invocationContext);

        //then
        then(sessionFactory.getCurrentSession()).should().beginTransaction();
    }


    @Test
    @DisplayName("should commit transaction")
    void shouldCommitTransaction() throws Exception {
        //when

        transactionInterceptor.doInTransaction(invocationContext);

        //then
        then(sessionFactory.getCurrentSession().getTransaction()).should().commit();
    }


    @Test
    @DisplayName("should rollback transaction when there is any exception")
    void shouldRollbackTransactionWhenThereIsAnyException() throws Exception {
        //given
        given(invocationContext.proceed()).willThrow(new Exception("message"));
        given(sessionFactory.getCurrentSession()
                            .getTransaction()
                            .isActive()).willReturn(true);

        //when
        catchThrowable(() -> {
            transactionInterceptor.doInTransaction(invocationContext);
        });

        //then
        then(sessionFactory.getCurrentSession()
                           .getTransaction()).should()
                                             .rollback();
    }

}