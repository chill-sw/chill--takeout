package com.chill.controller;

import com.chill.common.Result;
import com.chill.entity.Employee;
import com.chill.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Employee employee){
        return employeeService.login(request,employee);
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("employee");
        return Result.success("退出成功！");
    }

    //添加员工
    @PostMapping()
    public Result save(@RequestBody Employee employee){
        return  employeeService.saveEmployee(employee);
    }

    //分页查询
    @GetMapping("/page")
    public Result page(
            @RequestParam(value = "page") int pageNum,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "") String name
            ){
        return employeeService.queryPage(pageNum,pageSize,name);
    }

    @PutMapping()
    public Result updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @GetMapping("{id}")
    public Result getEmployeeOne(@PathVariable Long id){
         return employeeService.queryById(id);
    }
}
