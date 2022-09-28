package ru.sky.pro.SpringProj2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
        private String fullname;
        private String department;
        private double salary;
}
