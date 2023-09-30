package pro.sky.Employee.service;

import org.springframework.stereotype.Service;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;
import pro.sky.Employee.exceptions.EmployeeStoragelsFullException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees;

    private static final int MAX_NUMBER_OF_EMPLOYEE = 11;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }


    @Override
    public void addEmployee(Employee employee) {

        if (MAX_NUMBER_OF_EMPLOYEE == employees.size()) {
            throw new EmployeeStoragelsFullException();
        }

        if (employees.containsKey(generateKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);

    }

    @Override
    public void removeEmployee(Employee employee) {
        if (!employees.containsKey(generateKey(employee))) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(generateKey(employee));
        }
    }

    @Override
    public void findEmployee(Employee employee) {
        if (!employees.containsKey(generateKey(employee))) {
            throw new EmployeeNotFoundException();
        } else {
            employees.get(generateKey(employee));
        }
    }

    @Override
    public Collection<Employee> outputEmployee() {
        return employees.values();
    }

    private String generateKey(Employee employee) {
        return employee.getFullName();
    }
}
