package pro.sky.Employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employee.dto.Employee;
import pro.sky.Employee.service.DepartmentService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public String maxSalaryEmployee(@RequestParam("department") int department) {
        return "employee with max salary in " + department + "department: " +
                departmentService.maxSalaryEmployee(department);
    }
    @GetMapping("/min-salary")
    public String minSalaryEmployee(@RequestParam("department") int department) {
        return "employee with min salary in " + department + "department: " +
                departmentService.minSalaryEmployee(department);
    }
    @GetMapping("/all")
    public String getEmployeesInDepartment(@RequestParam("department") int department) {
        return "all employees in " + department + "department: " +
                departmentService.getEmployeesInDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, Collection<Employee>> getAll() {
        return departmentService.getAll();
    }
}
