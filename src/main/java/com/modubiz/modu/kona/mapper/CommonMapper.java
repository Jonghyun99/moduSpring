package com.modubiz.modu.kona.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

    /**
     * konaUserId 조회
     * param:konaId
     * return:konaUserId
     * */
    String getKonaUserId(String strParam);

}
