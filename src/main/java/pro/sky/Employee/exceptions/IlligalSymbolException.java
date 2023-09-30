package pro.sky.Employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Должны быть только буквы")
public class IlligalSymbolException extends IllegalArgumentException{
}
