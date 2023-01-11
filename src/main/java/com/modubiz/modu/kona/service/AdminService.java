package com.modubiz.modu.kona.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modubiz.modu.common.constants.Constants;
import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.admin.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService extends CommonService implements CommonUtilIF {

    @Autowired
    CommonService commonService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ObjectMapper mapper;

    @Value("${kona.host}")
    private String konaHost;

    @Value("${kona.port}")
    private String konaPort;

    @Value("${kona.context.path}")
    private String konaContextPath;

    @Value("${kona.asp.id}")
    private String konaAspId;

    @Value("${kona.admin.recharge.url}")
    private String adminRechargeUrl;

    @Value("${kona.admin.reversal.url}")
    private String adminReversalUrl;

    @Value("${kona.admin.refund.url}")
    private String adminRefundUrl;

    @Value("${kona.admin.partialRefund.url}")
    private String adminPartialRefundUrl;

    @Value("${kona.rechargerId}")
    private String rechargerId;


    /**
     * 어드민 충전
     */
    public RechargeResDto recharge(RechargeReqDto requestDto) {
        RechargeResDto responseDto = new RechargeResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값
            requestDto.setRechargerId(rechargerId);     //충전상ID

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, adminRechargeUrl);

            if(isNotBlank(strRtn)) {
                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, RechargeResDto.class);
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 어드민 충전
     */
    public ReversalResDto reversal(ReversalReqDto requestDto) {
        ReversalResDto responseDto = new ReversalResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, adminReversalUrl);

            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, ReversalResDto.class);

                // 성공시 처리
                if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //충전 성공 DB 처리

                } else {
                    //충전 실패 DB 처리
                }
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 어드민 환불
     */
    public RefundResDto refund(RefundReqDto requestDto) {
        RefundResDto responseDto = new RefundResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값
            requestDto.setRechargerId(rechargerId);
            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, adminRefundUrl);

            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, RefundResDto.class);

            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 어드민 부분환불
     */
    public RefundResDto partialRefund(PartialRefundReqDto requestDto) {
        RefundResDto responseDto = new RefundResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값
            requestDto.setRechargerId(rechargerId);
            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, adminPartialRefundUrl);

            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, RefundResDto.class);

            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

}
