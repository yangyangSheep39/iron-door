package com.sheep.advanced.monomer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.advanced.monomer.entity.MyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangyangSheep
 * @ClassName MyUserMapper.java
 * @createTime 2021/05/13 17:09
 * @Description 用户查询Mapper
 */
@Mapper
public interface MyUserMapper extends BaseMapper<MyUser> {
}
