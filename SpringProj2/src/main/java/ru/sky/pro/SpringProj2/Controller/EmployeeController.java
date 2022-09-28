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
import java.util.List;
import java.util.Map;

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
    public Employee addEmployee(@RequestParam(value = "fullName") String fullName,
                                @RequestParam(value = "salary") double salary,
                                @RequestParam(value = "department") String department) {
        if (null == fullName || "".equals(fullName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.addEmployee(fullName,department,salary);
        } catch (EmployeeAlreadyAddedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такое рабочий уже есть");
        }
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam(value = "fullName") String fullName) {
        if (null == fullName || "".equals(fullName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.deleteEmployee(fullName);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Рабочий не найден");
        }

    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam(value = "fullName") String fullName) {
        if (null == fullName || "".equals(fullName)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        try {
            return employeeService.findEmployee(fullName);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Рабочий не найден");
        }
    }

    @GetMapping("/departments/max-salary")
    public Employee findEmployeewithMaxSalary(@RequestParam(value = "departmentId") String department) {
        if (null == department || "".equals(department)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        return employeeService.getEmployeesFromDepartAndMaxSal(department);
    }

    @GetMapping("/departments/min-salary")
    public Employee findEmployeewithMinSalary(@RequestParam(value = "departmentId") String department) {
        if (null == department || "".equals(department)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        return employeeService.getEmployeesFromDepartAndMinSal(department);
    }


    @GetMapping("/departments/all")
    public List<Employee> findAllEmployeeWithDepart(@RequestParam(value = "departmentId") String department) {
        if (null == department || "".equals(department)) {
            throw new IllegalArgumentException("Введите все данные");
        }
        return employeeService.getEmployeesFromDepart(department);
    }

    @GetMapping("/all")
    public Map<String, List<Employee>> findAllEmployeeWithDepart() {
        return employeeService.getEmployeeAndDepartment();
    }

}

