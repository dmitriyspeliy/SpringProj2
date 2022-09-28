package ru.sky.pro.SpringProj2.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.sky.pro.SpringProj2.Entity.Employee;
import ru.sky.pro.SpringProj2.HandleException.EmployeeAlreadyAddedException;
import ru.sky.pro.SpringProj2.HandleException.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class EmployeeService {
    private final ArrayList<Employee> arr = new ArrayList<>();

    {
        arr.add(new Employee("Pospelov Dmitriy Alexsandrovich", "1", 13.000));
        arr.add(new Employee("Ivanov Ivan Ivanovich", "2", 14.0000));
        arr.add(new Employee("Petrov Pety Petrovich", "3", 12.000));
        arr.add(new Employee("Pogorelov Pavel Alexsandrovich", "1", 15.000));
        arr.add(new Employee("Poter Gary", "2", 21.000));
        arr.add(new Employee("Korostelev Alexsey Vladimirovich", "3", 4.000));
        arr.add(new Employee("Luhina Anastasiya Ivanovna", "1", 5.400));
        arr.add(new Employee("Romanova Anna Olegovna", "3", 12.100));
        arr.add(new Employee("Pospelova Daria Alexsandrovna", "3", 11.900));
        arr.add(new Employee("Anikina Olga Ivanovna", "2", 21.102));
    }

    //найти работника
    public Employee findEmployee(String fullName) throws EmployeeNotFoundException {
        return arr.stream().filter(x -> x.getFullname().equals(fullName)).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }

    //удалить работника
    public Employee deleteEmployee(String fullName) throws EmployeeNotFoundException {
        Employee employee = null;
        employee = arr.stream().filter(x -> x.getFullname().equals(fullName)).findFirst().orElseThrow(EmployeeNotFoundException::new);
        arr.remove(employee);
        return employee;

    }

    //Не добавил исключение EmployeeStorageIsFullException, так как массив динамический,только проверка
    //на наличие работника и исключение EmployeeAlreadyAddedException
    //добавить работника
    public Employee addEmployee(String fullName, String department, double salary) throws EmployeeAlreadyAddedException {
        boolean result = arr.stream().anyMatch(x -> x.getFullname().equals(fullName));
        Employee employee = null;
        if (!result) {
            employee = new Employee(fullName, department, salary);
            arr.add(employee);
        } else {
            throw new EmployeeAlreadyAddedException();
        }
        return employee;
    }

    //получить всех работников
    public ArrayList<Employee> getAll() {
        return getArr();
    }

    //Получить сотрудника с максимальной зарплатой по отделу.
    public Employee getEmployeesFromDepartAndMaxSal(String department) {
        return arr.stream().filter(x -> x.getDepartment().equals(department)).max(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    //Получить сотрудника с минимальной зарплатой по отделу.
    public Employee getEmployeesFromDepartAndMinSal(String department) {
        return arr.stream().filter(x -> x.getDepartment().equals(department)).min(Comparator.comparingDouble(Employee::getSalary)).get();
    }

    //Возвращать всех сотрудников по отделу.
    public List<Employee> getEmployeesFromDepart(String department) {
        return arr.stream().filter(x -> x.getDepartment().equals(department)).collect(Collectors.toList());
    }

    //Возвращать всех сотрудников с разделением по отделам
    public Map<String, List<Employee>> getEmployeeAndDepartment() {
        Map<String, List<Employee>> sortedMap = new TreeMap<>();
        for (Employee employee : arr) {
            if (employee != null) {
                sortedMap.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>()).add(employee);
            }
        }
        return sortedMap;
    }
}
