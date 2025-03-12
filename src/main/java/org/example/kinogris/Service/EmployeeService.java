package org.example.kinogris.Service;

import jakarta.validation.Valid;
import org.example.kinogris.Model.Employee;
import org.example.kinogris.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(int id, @Valid Employee employeeDetails) {
        if (id <= 0){
            throw new IllegalArgumentException("id must be greater than 0");
        }
        return employeeRepository.findById(id).map(employee1 -> {
            employee1.setName(employeeDetails.getName());
            employee1.setRole(employeeDetails.getRole());
            return employeeRepository.save(employee1);
                });
    }

    public boolean deleteEmployee(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("id must be greater than 0");
        }
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
