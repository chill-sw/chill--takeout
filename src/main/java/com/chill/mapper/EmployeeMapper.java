package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chill.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
