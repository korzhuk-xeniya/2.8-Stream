package pro.sky.Employee.service;

import pro.sky.Employee.dto.Employee;

import java.util.Collection;

public interface EmployeeService {

        void addEmployee(Employee employee);

        void removeEmployee(Employee employee);

        void findEmployee(Employee employee);

        Collection outputEmployee();
}
