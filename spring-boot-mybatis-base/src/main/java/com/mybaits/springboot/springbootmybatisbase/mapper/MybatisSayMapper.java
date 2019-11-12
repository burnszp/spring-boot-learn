package com.mybaits.springboot.springbootmybatisbase.mapper;


public interface MybatisSayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MybatisSay record);

    int insertSelective(MybatisSay record);

    MybatisSay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MybatisSay record);

    int updateByPrimaryKey(MybatisSay record);
}