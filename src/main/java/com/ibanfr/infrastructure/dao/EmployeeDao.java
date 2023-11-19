package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Employee;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

@Dependent
public class EmployeeDao extends BaseDao<Employee>{

    @Inject
    public EmployeeDao(SessionFactory sessionFactory) {
        super(Employee.class, sessionFactory);
    }
}
