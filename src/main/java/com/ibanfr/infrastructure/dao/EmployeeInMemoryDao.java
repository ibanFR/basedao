package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import com.ibanfr.domain.model.EmployeeRepository;
import jakarta.enterprise.context.Dependent;

@Dependent
public class EmployeeInMemoryDao extends InMemoryDao<Employee> implements EmployeeRepository {
}
