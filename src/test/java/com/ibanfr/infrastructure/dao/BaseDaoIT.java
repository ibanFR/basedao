package com.ibanfr.infrastructure.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.jboss.weld.junit5.auto.AddEnabledInterceptors;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoWeld
@AddEnabledInterceptors(TransactionInterceptor.class)
class BaseDaoIT {

    final Map<String, Object> settings = Map.of("hibernate.connection.url",
                                "jdbc:h2:mem:testdb",
                                "hibernate.connection.driver_class",
                                "org.h2.Driver",
                                "hibernate.hbm2ddl.auto",
                                "update",
                                "hibernate.show_sql",
                                "true", "hibernate.current_session_context_class", "thread");


    @Produces
    @ApplicationScoped
    SessionFactory sessionFactory = new SessionFactoryProducer().produceSessionFactory(settings, FooEntity.class);

    @Inject
    BaseDao<FooEntity> baseDao;


    @Test
    @DisplayName("should create new instance")
    void should_create_new_instance() {
        //then
        assertThat(baseDao).isNotNull();
    }

    @Test
    @DisplayName("should save Foo entity")
    void should_save_entity() {
        //given
        FooEntity foo = FooEntity.builder().build();
        //when
        baseDao.save(foo);

        //then
        assertThat(foo.getId()).isNotNull()
                                   .isEqualTo(1);
    }

}
