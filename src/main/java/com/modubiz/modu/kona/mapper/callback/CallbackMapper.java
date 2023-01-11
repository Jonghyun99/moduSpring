package com.modubiz.modu.kona.mapper.callback;

import com.modubiz.modu.kona.dto.callback.CardTransactionReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CallbackMapper {


    Map<String, Object> getChargingInfo(Map<String, Object> mapParam);

    Map<String, Object> getMemberInfo(Map<String, Object> mapParam);

    int updateCharging(Map<String, Object> mapParam);

    String getFbkNo();

    int chargeStep1(Map<String, Object> mapParam);

    Map<String, Object> chargeStep2(Map<String, Object> mapParam);

    int updateCharge(Map<String, Object> mapParam);


    int insertKonaCardTrans(CardTransactionReqDto dtoParam);

}
