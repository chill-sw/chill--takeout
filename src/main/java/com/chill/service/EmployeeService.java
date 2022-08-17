package com.chill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.entity.Employee;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface EmployeeService extends IService<Employee> {
    Result login(HttpServletRequest request, Employee employee);

    Result saveEmployee(Employee employee);

    Result queryPage(int pageNum, int pageSize, String name);

    Result updateEmployee(Employee employee);

    Result queryById(Long id);

}
