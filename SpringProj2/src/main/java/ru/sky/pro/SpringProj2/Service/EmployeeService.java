package ru.sky.pro.SpringProj2.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.sky.pro.SpringProj2.Entity.Employee;
import ru.sky.pro.SpringProj2.HandleException.EmployeeAlreadyAddedException;
import ru.sky.pro.SpringProj2.HandleException.EmployeeNotFoundException;

import java.util.ArrayList;

@Data
@Service
public class EmployeeService {
    private final ArrayList<Employee> arr = new ArrayList<>();

    {
        arr.add(new Employee("Pospelov", "Dmitriy"));
        arr.add(new Employee("Ivanov", "Ivanovich"));
        arr.add(new Employee("Petrov", "Petrovich"));
        arr.add(new Employee("Pogorelov", "Pavel"));
        arr.add(new Employee("Poter", "Gary"));
        arr.add(new Employee("Korostelev", "Alexsey"));
        arr.add(new Employee("Luhina", "Anastasiya"));
        arr.add(new Employee("Romanova", "Anna"));
        arr.add(new Employee("Pospelova", "Daria"));
        arr.add(new Employee("Anikina", "Olga"));
    }

    //найти работника
    public Employee findEmployee(String lastName) throws EmployeeNotFoundException {
        return arr.stream().filter(x -> x.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    //удалить работника
    public Employee deleteEmployee(String lastName) throws EmployeeNotFoundException {
        Employee employee = null;
        employee = arr.stream().filter(x -> x.getLastName()
                        .equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        arr.remove(employee);
        return employee;

    }

    //Не добавил исключение EmployeeStorageIsFullException, так как массив динамический,только проверка
    //на наличие работника и исключение EmployeeAlreadyAddedException
    //добавить работника
    public Employee addEmployee(String lastName, String firstName) throws EmployeeAlreadyAddedException {
        boolean result = arr.stream()
                .anyMatch(x -> x.getLastName().equals(lastName));
        Employee employee = null;
        if (!result) {
            employee = new Employee(lastName, firstName);
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
}
