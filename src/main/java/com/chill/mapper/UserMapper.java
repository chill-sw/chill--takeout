package com.chill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chill.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
