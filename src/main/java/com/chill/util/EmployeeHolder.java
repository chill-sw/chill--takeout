package com.chill.util;


import com.chill.entity.Employee;

public class EmployeeHolder {
    private static final ThreadLocal<Employee> tl = new ThreadLocal<>();

    public static void saveEmployee(Employee employee){
        tl.set(employee);
    }
    public static Employee getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
