package pro.sky.Employee.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> employees = List.of(
            new Employee("Alex", "Ivanov", 2, 90_000),
            new Employee("Oleg", "Matveev", 2, 200_000),
            new Employee("Zena", "Lavrova", 5, 250_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesThereAreInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(employees);
        Employee result = departmentService.maxSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryEmployee_shouldThrowExceptionWhenThereAreNotEmployeesInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalaryEmployee(9));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMinSalaryWhenEmployeesThereAreInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(employees);
        Employee result = departmentService.minSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(0), result);
    }

    @Test
    void minSalaryEmployee_shouldThrowExceptionWhenThereAreNotEmployeesInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalaryEmployee(9));
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmployeesCollectionWhenEmployeeThereIsInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(employees);
        Collection<Employee> result = departmentService.getEmployeesInDepartment(employees.get(0).getDepartment());
        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmptyMapWhenThereAreNotEmployeesInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(Collections.emptyList());
        Collection<Employee> all = employeeService.outputEmployee();

        assertTrue(all.isEmpty());
    }

    @Test
    void getAll_shouldReturnMapWithEmployeeWhenEmployeeThereIsInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                employees.get(0).getDepartment(), List.of(employees.get(0), employees.get(1)),
                employees.get(2).getDepartment(), List.of(employees.get(2)));

        Map<Integer, List<Employee>> result = departmentService.getAll();

        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_shouldReturnEmptyMapWhenThereAreNotEmployeesInDepartment() {
        when(employeeService.outputEmployee()).thenReturn(Collections.emptyList());

        Collection<Employee> all = employeeService.outputEmployee();

        assertTrue(all.isEmpty());
    }

}