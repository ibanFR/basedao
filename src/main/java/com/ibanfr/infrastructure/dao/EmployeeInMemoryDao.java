package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.Employee;
import jakarta.enterprise.context.Dependent;

@Dependent
public class EmployeeInMemoryDao extends InMemoryDao<Employee> {
}
