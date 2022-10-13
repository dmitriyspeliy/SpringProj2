package ru.sky.pro.SpringProj2.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sky.pro.SpringProj2.Entity.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService out;

    static ArrayList<Employee> newArrList = new ArrayList<>();

    @BeforeEach
    public void init(){
        newArrList.add(new Employee("MAXVALUE","1",Double.MAX_VALUE));
        newArrList.add(new Employee("MINVALUE","1",Double.MIN_VALUE));
        newArrList.add(new Employee("test","1",10.200));
        newArrList.add(new Employee("test1","2",10.100));
        when(employeeService.getArr())
                .thenReturn(newArrList);
    }

    @Test
    void getEmployeesFromDepartAndMaxSal() {
       assertEquals(out.getEmployeesFromDepartAndMaxSal("1").getFullname(),"MAXVALUE");
    }

    @Test
    void getEmployeesFromDepartAndMinSal() {
        assertEquals(out.getEmployeesFromDepartAndMinSal("1").getFullname(),"MINVALUE");
    }

    @Test
    void getEmployeesFromDepart() {
        boolean check = out.getEmployeesFromDepart("1").stream().allMatch(x -> Objects.equals(x.getDepartment(), "1"));
        assertTrue(check);
    }


}