package ru.sky.pro.SpringProj2.HandleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeAlreadyAddedException extends Exception {
    public EmployeeAlreadyAddedException() {
        super("Такое рабочий уже есть");
    }
}
