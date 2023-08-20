package pro.sky.Employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "В базе есть уже такой сотрудник")
public class EmployeeAlreadyAddedException extends RuntimeException  {
}
