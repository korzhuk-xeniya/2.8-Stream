package pro.sky.Employee.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;
import pro.sky.Employee.exceptions.EmployeeStoragelsFullException;
import pro.sky.Employee.service.EmployeeService;
import pro.sky.Employee.util.EmployeeNameValidator;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection outputEmployee() {
        return employeeService.outputEmployee();
    }


    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") int department,
                              @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);
        try {
            employeeService.addEmployee(firstName, lastName, department, salary);
        } catch (EmployeeStoragelsFullException | EmployeeAlreadyAddedException ex) {
            return (ex.getMessage());
        }
        return " Employee " + firstName + " " + lastName + " " + department + " " + salary + " added ";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("department") int department,
                                 @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);

        try {
            employeeService.removeEmployee(firstName, lastName, department, salary);
        } catch (EmployeeNotFoundException ex) {
            return (ex.getMessage());
        }
        return " Employee" + firstName + " " + lastName + " removed ";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("department") int department,
                               @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);

        try {
            employeeService.findEmployee(firstName, lastName, department, salary);
        } catch (EmployeeNotFoundException ex) {
            return (ex.getMessage());
        }
        return " Employee " + firstName + " " + lastName + " " + department + " " + salary + "found ";
    }
}
