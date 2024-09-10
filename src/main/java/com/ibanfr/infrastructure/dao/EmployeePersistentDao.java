package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import com.ibanfr.domain.model.EmployeeRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

@Dependent
public class EmployeePersistentDao extends BaseDao<Employee> implements EmployeeRepository {

    @Inject
    public EmployeePersistentDao(SessionFactory sessionFactory) {
        super(Employee.class, sessionFactory);
    }
}
