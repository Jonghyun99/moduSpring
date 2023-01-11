package com.modubiz.modu.kona.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    Map<String, Object> memberTest();

}
