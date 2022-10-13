package ru.sky.pro.SpringProj2.Service;

import org.junit.jupiter.api.Test;
import ru.sky.pro.SpringProj2.Entity.Employee;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService out = new EmployeeService();

    @Test
    void findEmployee() {
        out.addEmployee("test test", "1", 12.300);
        Employee expected = new Employee("Test test", "1", 12.300);
        assertEquals(expected, out.findEmployee("Test test"));
    }

    @Test
    void findEmployeeIfNotExist() {
        out.addEmployee("test test", "1", 12.300);
        assertThrows(RuntimeException.class, () -> out.findEmployee("Ivan test"));
    }

    @Test
    void deleteEmployee() {
        out.addEmployee("test test", "1", 12.300);
        Employee expected = new Employee("Test test", "1", 12.300);
        assertEquals(expected, out.deleteEmployee("Test test"));
    }

    @Test
    void deleteEmployeeIfNotExist() {
        out.addEmployee("test test", "1", 12.300);
        assertThrows(RuntimeException.class, () -> out.deleteEmployee("Ivan test"));
    }

    @Test
    void addEmployee() {
        out.addEmployee("test test", "1", 12.300);
        Employee expected = new Employee("Test test", "1", 12.300);
        assertEquals(expected, out.findEmployee("Test test"));
    }

    @Test
    void addEmployeeIfExist() {
        out.addEmployee("test test", "1", 12.300);
        assertThrows(RuntimeException.class, () -> out.addEmployee("test test", "1", 12.300));
    }

    @Test
    void getArr() {
        assertNotNull(out.getArr());
    }
}