package com.sheep.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.quickstart.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangyangSheep
 * @ClassName MyUserMapper.java
 * @createTime 2021/05/13 17:09
 * @Description
 */
@Mapper
public interface MyUserMapper extends BaseMapper<MyUser> {
}
