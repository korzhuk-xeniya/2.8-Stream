package pro.sky.Employee.util;

import org.apache.commons.lang3.StringUtils;
import pro.sky.Employee.exceptions.IlligalSymbolException;

public class EmployeeNameValidator {
    public static void  validateIsAlpha(String... names) {
        for (String name : names) {
             if (!StringUtils.isAlpha(name)) {
                 throw new IlligalSymbolException();
             }
        }
    }
}
