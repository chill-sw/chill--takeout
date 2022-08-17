package com.chill.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chill.common.Result;
import com.chill.entity.Employee;
import com.chill.mapper.EmployeeMapper;
import com.chill.service.EmployeeService;
import com.chill.util.BaseContext;
import com.chill.util.EmployeeHolder;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public Result login(HttpServletRequest request, Employee employee) {
        //将提交的密码进行MD5处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee emp = query().eq("username", employee.getUsername()).eq("password", password).one();
        if (emp == null ){
            return Result.error("登录失败");
        }
        //查看封禁状态
        if (emp.getStatus() == 0){
            return Result.error("账户已被禁用！");
        }
//        request.setAttribute("employee",employee.getId());
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }

    @Override
    public Result saveEmployee(Employee employee) {
        String idNumber = employee.getIdNumber().substring(12);
        //身份证后6位作为密码
        employee.setPassword(DigestUtils.md5DigestAsHex(idNumber.getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(EmployeeHolder.getUser().getId());
//        employee.setCreateUser(EmployeeHolder.getUser().getId());
        save(employee);
        return Result.success("success");
    }

    @Override
    public Result queryPage(int pageNum, int pageSize, String name) {
        Page<Employee> page = query().like(StrUtil.isNotBlank(name),"name",name).orderByAsc("update_time").page(new Page<>(pageNum,pageSize));
        return Result.success(page);
    }

    @Override
    public Result updateEmployee(Employee employee) {
        Long id = BaseContext.getCurrentId();
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(id);
        updateById(employee);
        return Result.success("success");
    }

    @Override
    public Result queryById(Long id) {
        Employee employee = getById(id);
        if (ObjectUtil.isEmpty(employee)){
            return Result.error("没有这个员工");
        }
        return Result.success(employee);
    }
}
