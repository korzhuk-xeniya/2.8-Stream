package pro.sky.Employee.service;

import org.junit.jupiter.api.Test;
import pro.sky.Employee.dto.Employee;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeServiceImplTest {
private EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    @Test
    void addEmployee_shouldAddEmployeeToMapAndReturnEmployee() {
        String firstName = "Alex";
        String lastName = "Ivanov";
        int department = 2;
        double salary = 90_000;
        Employee empl = new Employee(firstName, lastName,department, salary);

        Employee result = underTest.addEmployee(empl);


    }

    @Test
    void removeEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void outputEmployee() {
    }
}