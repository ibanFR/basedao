package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import com.ibanfr.domain.model.EmployeeRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

@Dependent
public class EmployeeDao extends BaseDao<Employee> implements EmployeeRepository {

    @Inject
    public EmployeeDao(SessionFactory sessionFactory) {
        super(Employee.class, sessionFactory);
    }
}
