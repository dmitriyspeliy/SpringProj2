package ru.sky.pro.SpringProj2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.sky.pro.SpringProj2.Entity.Employee;
import ru.sky.pro.SpringProj2.HandleException.EmployeeAlreadyAddedException;
import ru.sky.pro.SpringProj2.HandleException.EmployeeNotFoundException;
import ru.sky.pro.SpringProj2.Service.EmployeeService;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public ArrayList<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        if (null == firstName || null == lastName
                || "".equals(firstName) || "".equals(lastName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.addEmployee(lastName, firstName);
        } catch (EmployeeAlreadyAddedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такое рабочий уже есть");
        }
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        if (null == firstName || null == lastName
                || "".equals(firstName) || "".equals(lastName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.deleteEmployee(lastName);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Рабочий не найден");
        }

    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        if (null == firstName || null == lastName
                || "".equals(firstName) || "".equals(lastName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.findEmployee(lastName);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Рабочий не найден");
        }
    }

}

