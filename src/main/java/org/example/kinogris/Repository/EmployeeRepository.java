package org.example.kinogris.Repository;

import org.example.kinogris.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Set<Employee> findByName(String name);
}
