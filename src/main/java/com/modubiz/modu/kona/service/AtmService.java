package com.modubiz.modu.kona.service;

import com.modubiz.modu.common.constants.Constants;
import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.atm.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.dto.common.CommonResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AtmService extends CommonService implements CommonUtilIF {

    @Value("${kona.atm.onetimetoken.url}")
    private String konaAtmOneTimeTokenUrl;

    @Value("${kona.atm.inquiry.url}")
    private String konaAtmInquiryUrl;

    @Value("${kona.atm.reservation.url}")
    private String konaAtmReservationUrl;

    @Value("${kona.atm.reservation.cancel.url}")
    private String konaAtmReservationCancelUrl;


    /**
     * ATM 토큰 발급 서비스
     * */
    public AtmOneTimeTokenResDto issueOneTimeToken(AtmOneTimeTokenReqDto requestDto) {
        AtmOneTimeTokenResDto responseDto = new AtmOneTimeTokenResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaAtmOneTimeTokenUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, AtmOneTimeTokenResDto.class);

                // 성공시 처리
                if (isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //성공 DB처리

                } else {
                    //실패 DB 처리
                }
            // 응답값 못받은 경우?
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * ATM 출금 금액 조회 서비스
     * */
    public AtmInquiryResDto inquiryRefundAmount(AtmInquiryReqDto requestDto) {
        AtmInquiryResDto responseDto = new AtmInquiryResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaAtmInquiryUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, AtmInquiryResDto.class);

                // 성공시 처리
                if (isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //성공 DB처리
                } else {
                    //실패 DB 처리
                }
        // 응답값 못받은 경우?
        } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * ATM 출금 예약 서비스
     * */
    public AtmReservationResDto reservationRefund(AtmReservationReqDto requestDto) {
        AtmReservationResDto responseDto = new AtmReservationResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaAtmReservationUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, AtmReservationResDto.class);

                // 성공시 처리
                if (isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //성공 DB처리

                } else {
                    //실패 DB 처리
                }
                // 응답값 못받은 경우?
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * ATM 출금 예약 취소 서비스
     * */
    public CommonResponseWrapper cancelRefundReservation(AtmReservationCancelReqDto requestDto) {
        CommonResponseWrapper responseDto = new CommonResponseWrapper();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaAtmReservationCancelUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CommonResponseWrapper.class);

                // 성공시 처리
                if (isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    // 성공 DB처리

                } else {
                    // 실패 DB 처리
                }
                // 응답값 못받은 경우?
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }
}
