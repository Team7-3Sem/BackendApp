package org.example.kinogris.Repository;

import org.example.kinogris.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Set<Employee> findByName(String name);
}
