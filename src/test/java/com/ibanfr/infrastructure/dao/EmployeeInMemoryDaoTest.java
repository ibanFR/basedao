package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class EmployeeInMemoryDaoTest {

    EmployeeInMemoryDao employeeInMemoryDao = new EmployeeInMemoryDao();

    @Test
    @DisplayName("should save ivan")
    void shouldSaveEmployee() {

        //given
        Employee ivan = Employee.of("Ivan");

        //when
        employeeInMemoryDao.save(ivan);

        //then
        assertThat(ivan.getId()).isNotNull();
    }

    @Nested
    @DisplayName("Find by Id")
    class FindById {

        Employee ivan;

        @BeforeEach
        void setUp() {
            //given
            ivan = Employee.of("Ivan");

            employeeInMemoryDao.save(ivan);
        }

        @Test
        @DisplayName("should find ivan by the given id")
        void shouldFindEmployeeByTheGivenId() {
            //when
            Optional<Employee> optionalFoo = employeeInMemoryDao.findById(ivan.getId());

            //then
            assertThat(optionalFoo.get())
                    .isEqualTo(ivan);
        }

    }
}