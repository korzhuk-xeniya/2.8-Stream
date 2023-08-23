package pro.sky.Employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employee.exceptions.EmployeeNotFoundException;
import pro.sky.Employee.exceptions.EmployeeStoragelsFullException;
import pro.sky.Employee.service.EmployeeService;

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
        Employee employee = new Employee(firstName, lastName, department, salary);
        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeStoragelsFullException | EmployeeAlreadyAddedException ex) {
            return (ex.getMessage());
        }
        return " Employee " + employee + " added ";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("department") int department,
                                 @RequestParam("salary") double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        try {
            employeeService.removeEmployee(employee);
        } catch (EmployeeNotFoundException ex) {
            return (ex.getMessage());
        }
        return " Employee" + employee + " removed ";
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("department") int department,
                               @RequestParam("salary") double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        try {
            employeeService.findEmployee(employee);
        } catch (EmployeeNotFoundException ex) {
            return (ex.getMessage());
        }
        return " Employee " + employee + "found ";
    }
}
//ролдл