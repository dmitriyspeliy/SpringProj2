package ru.sky.pro.SpringProj2.HandleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeStorageIsFullException extends Exception {
    public EmployeeStorageIsFullException() {
        super("Массив переполнен");
    }
}
