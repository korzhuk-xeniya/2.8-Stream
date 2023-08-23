package pro.sky.Employee.service;

import org.springframework.stereotype.Service;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryEmployee(int department) {
        return employeeService.outputEmployee().stream()
                .filter(employee -> department == employee.getDepartment())
                .max(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee minSalaryEmployee(int department) {
        return employeeService.outputEmployee().stream()
                .filter(empl -> empl.getDepartment() == department)
                .min(Comparator.comparing(empl -> empl.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getEmployeesInDepartment(int department) {
        return employeeService.outputEmployee().stream()
                .filter(empl -> empl.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.outputEmployee().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));

    }
}
