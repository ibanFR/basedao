package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.jboss.weld.junit5.auto.AddEnabledInterceptors;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoWeld
@AddEnabledInterceptors(TransactionInterceptor.class)
class EmployeePersistentDaoIT {

    final Map<String, Object> settings = Map.of("hibernate.connection.url",
                                                "jdbc:h2:mem:testdb",
                                                "hibernate.connection.driver_class",
                                                "org.h2.Driver",
                                                "hibernate.hbm2ddl.auto",
                                                "update",
                                                //"hibernate.show_sql",
                                                //"true",
                                                "hibernate.current_session_context_class",
                                                "thread");

    @Produces
    @ApplicationScoped
    SessionFactory sessionFactory = new SessionFactoryProducer().produceSessionFactory(settings,
                                                                                       Employee.class);

    @Inject
    EmployeePersistentDao employeeDao;


    @Test
    @DisplayName("should create new instance")
    void should_create_new_instance() {
        //then
        assertThat(employeeDao).isNotNull();
    }

    @Test
    @DisplayName("should save Foo entity")
    void should_save_entity() {
        //given
        Employee employee = Employee.builder()
                .name("Ivan")
                .build();
        //when
        employeeDao.save(employee);

        //then
        assertThat(employee.getId())
                .isNotNull()
                .isPositive();
    }

    @Nested
    @DisplayName("Find by Id")
    class FindById {

        Employee employee;

        @BeforeEach
        void setUp() {
            //given
            employee = Employee.builder()
                    .name("Ivan")
                    .build();

            employeeDao.save(employee);
        }

        @Test
        @DisplayName("should find entity by the given id")
        void shouldFindEntityByTheGivenId() {
            //when
            Optional<Employee> optionalFoo = employeeDao.findById(employee.getId());

            //then
            assertThat(optionalFoo)
                    .contains(employee);
        }

    }

}
