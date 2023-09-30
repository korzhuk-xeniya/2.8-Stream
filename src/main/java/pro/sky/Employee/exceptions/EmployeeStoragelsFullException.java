package pro.sky.Employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Превышен лимит сотрудников в компании")
public class EmployeeStoragelsFullException extends RuntimeException {
}
