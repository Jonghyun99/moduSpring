package com.modubiz.modu.kona.service;

import com.modubiz.modu.common.constants.Constants;
import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.admin.*;
import com.modubiz.modu.kona.dto.callback.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.mapper.callback.CallbackMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Slf4j
public class CallbackService extends CommonService implements CommonUtilIF {

    @Autowired
    CallbackMapper callbackMapper;

    /**
     * 충전 요청 내역 저장
     */
    @Transactional
    public RechargeReqDto insertChargingRequest(PaystoryVacntReqDto requestDto) {
        RechargeReqDto responseDto = new RechargeReqDto();
        String product = "";
        int chargingIdx;
        int paymentAmt;
        int chargeAmt;
        try {
            Map<String, Object> mapParam = makeEmptyMap();
            mapParam.put("vacntNo", requestDto.getVacntNo());
            mapParam.put("ordNo", requestDto.getOrdNo());

            Map<String, Object> memberParam = callbackMapper.getMemberInfo(mapParam);

            if(isEmpty(memberParam)) return null;

            int vacntFee = (int) memberParam.get("vacntFee");

            Map<String, Object> chargingParam = callbackMapper.getChargingInfo(mapParam);
            if(isEmpty(chargingParam)) {
                paymentAmt = Integer.valueOf(requestDto.getAmt());
                chargeAmt = paymentAmt - vacntFee;
                product = "MODU 포인트P["+chargeAmt+"] 충전";
                mapParam = makeEmptyMap();
                mapParam.put("memberIdx", getStr(memberParam, "memberIdx"));
                mapParam.put("id", getStr(memberParam, "id"));
                mapParam.put("name", getStr(memberParam, "name"));
                mapParam.put("account", getStr(memberParam, "accNumber"));
                mapParam.put("product", product);
                mapParam.put("amt", chargeAmt);
                mapParam.put("payment", paymentAmt);
                mapParam.put("pgNm", "PAYSTORY");
                mapParam.put("payMethod", "vacnt");

                chargingIdx = callbackMapper.chargeStep1(mapParam);
            } else {
                chargingIdx = getInt(chargingParam, "idx");
                paymentAmt = getInt(chargingParam, "amt");
            }

            mapParam = makeEmptyMap();
            mapParam.put("payMethod", "vacnt");
            mapParam.put("ordNo", requestDto.getOrdNo());
            mapParam.put("pgNm", "PAYSTORY");
            mapParam.put("idx", chargingIdx);
            callbackMapper.updateCharging(mapParam);

            String fbkNo = callbackMapper.getFbkNo();

            mapParam = makeEmptyMap();
            mapParam.put("fbkNo", fbkNo);
            mapParam.put("chargingIdx", chargingIdx);
            callbackMapper.chargeStep2(mapParam);

            responseDto.setServiceId(getStr(memberParam, "serviceId"));
            responseDto.setPar(getStr(memberParam, "par"));
            responseDto.setAmount(String.valueOf(paymentAmt));
            responseDto.setUserId(getStr(memberParam, "konaUserId"));
            responseDto.setMpaId(getStr(memberParam, "konaMpaId"));
        } catch(Exception e) {
            log.error("error", e);
            return null;
        }

        return responseDto;
    }

    @Transactional
    public UserUnregistrationResDto unregisterUser(UserUnregistrationReqDto requestDto) {
        UserUnregistrationResDto responseDto = new UserUnregistrationResDto();
        try {

            log.info("탈회회원 통보={}", requestDto);

            responseDto.setResponse(CommonResponseDto.builder().code("000_000").description("success").build());

        } catch(Exception e) {
            log.error("error", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    public boolean updateChargingRequest(PaystoryVacntReqDto requestDto, RechargeResDto responseDto) {

        Map<String, Object> mapParam = makeEmptyMap();

        mapParam.put("resultCd", responseDto.getResponse().getCode());
        mapParam.put("resultMsg", responseDto.getResponse().getDescription());
        mapParam.put("transactionId", responseDto.getTransactionId());
        mapParam.put("vacntNo", requestDto.getVacntNo());
        mapParam.put("ordNo", requestDto.getOrdNo());

        int cnt = callbackMapper.updateCharge(mapParam);

        if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
            if(cnt <= 0) {
                return false;
            }
        }
        return true;
    }


    public CardTransactionResDto insertKonaCardTrans(CardTransactionReqDto requestDto) {
        CardTransactionResDto responseDto = new CardTransactionResDto();
        responseDto.setResponse(CommonResponseDto.builder().code("000_000").description("성공").build());
        try {
            callbackMapper.insertKonaCardTrans(requestDto);
        } catch(Exception e) {
            log.error("insertKonaCardTrans", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }
        return responseDto;
    }

    public CardActivationResDto insertKonaCardActive(CardActivationReqDto requestDto) {
        CardActivationResDto responseDto = new CardActivationResDto();
        responseDto.setResponse(CommonResponseDto.builder().code("000_000").description("성공").build());
        try {
            // callbackMapper.insertKonaCardTrans(requestDto);
        } catch(Exception e) {
            log.error("insertKonaCardTrans", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }
        return responseDto;
    }


}
