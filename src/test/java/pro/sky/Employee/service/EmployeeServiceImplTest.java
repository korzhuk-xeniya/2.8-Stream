package pro.sky.Employee.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;
import pro.sky.Employee.exceptions.EmployeeStoragelsFullException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    private Employee expectedEmployee = new Employee("Alex", "Ivanov", 2, 90_000);

    @Test
    void addEmployee_shouldAddEmployeeToMapAndReturnEmployee() {

        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertTrue(underTest.outputEmployee().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenNotEnoughMapSize() {
        for (int i = 0; i < 11; i++) {
            underTest.addEmployee((expectedEmployee.getFirstName() + i),
                    (expectedEmployee.getLastName() + i),
                    (expectedEmployee.getDepartment() + i),
                    (expectedEmployee.getSalary() + i));
        }
        assertThrows(EmployeeStoragelsFullException.class, () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }
    @Test
    void addEmployee_shouldThrowExceptionWhenEqualEmployeeYetInMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }
    @Test
    void removeEmployee_shouldReturnEmployeeWhenEmployeeIsDeleted() {
        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertTrue(underTest.outputEmployee().contains(expectedEmployee));

        assertEquals(expectedEmployee, result);
    }
    @Test
    void removeEmployee_shouldThrowExceptionWhenEmployeeIsNotFoundInMap(){

        assertThrows(EmployeeNotFoundException.class, () -> underTest.removeEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }
    @Test
    void findEmployee_shouldReturnEmployeeWhenEmployeeIsInMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        Employee result = underTest.findEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertTrue(underTest.outputEmployee().contains(expectedEmployee));

        assertEquals(expectedEmployee, result);
    }
    @Test
    void findEmployee_shouldThrowExceptionWhenEmployeeIsNotFoundInMap(){

        assertThrows(EmployeeNotFoundException.class, () -> underTest.findEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }

    @Test
    void outputEmployee_shouldReternEmployeesListWhenEmployeeInMap() {
        Employee employee = new Employee("Nina", "Prokofeva", 1, 150_000);
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        underTest.addEmployee(employee.getFirstName(),
                employee.getLastName(), employee.getDepartment(), employee.getSalary());

        Collection<Employee> result = underTest.outputEmployee();

        assertTrue(result.containsAll(List.of(expectedEmployee, employee)));
    }
    @Test
    void outputEmployee_shouldReturnEmptyListWhenEmployeeIsNotInMap(){
        Collection<Employee> all = underTest.outputEmployee();

        assertTrue(all.isEmpty());
    }
}