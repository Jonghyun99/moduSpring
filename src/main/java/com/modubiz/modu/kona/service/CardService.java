package com.modubiz.modu.kona.service;

import com.modubiz.modu.common.constants.Constants;
import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.card.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardService  extends CommonService implements CommonUtilIF {

    @Value("${kona.card.register.url}")
    private String konaCardRegisterUrl;

    @Value("${kona.card.delivery.inquiry.url}")
    private String konaCardDeliveryInquiryUrl;

    @Value("${kona.card.transaction.inquiry.url}")
    private String konaCardTransactionInquiryUrl;

    @Value("${kona.card.list.inquiry.url}")
    private String konaCardListInquiryUrl;

    @Value("${kona.card.limit.inquiry.url}")
    private String konaCardLimitInquiryUrl;

    @Value("${kona.card.deduction.apply.url}")
    private String konaCardDeductionApplyUrl;


    /**
     * 카드등록 서비스
     * */
    public CardRegisterResDto registerCard(CardRegisterReqDto requestDto) {
        CardRegisterResDto responseDto = new CardRegisterResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaCardRegisterUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CardRegisterResDto.class);

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
     * 카드배송조회서비스
     * */
    public CardDeliveryInquiryResDto inquiryDeliveryCard(CardDeliveryInquiryReqDto requestDto) {
        CardDeliveryInquiryResDto responseDto = new CardDeliveryInquiryResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaCardDeliveryInquiryUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CardDeliveryInquiryResDto.class);

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
     * 카드 거래내역 조회 서비스
     * */
    public CardTransactionInquiryResDto inquiryTransactionCard(CardTransactionInquiryReqDto requestDto) {
        CardTransactionInquiryResDto responseDto = new CardTransactionInquiryResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaCardTransactionInquiryUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CardTransactionInquiryResDto.class);

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

    public CardListInquriyResDto inquiryCardList(CardListInquriyReqDto requestDto) {
        CardListInquriyResDto responseDto = new CardListInquriyResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaCardListInquiryUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CardListInquriyResDto.class);

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

    public LimitInquriyResDto inquiryCardLimit(LimitInquriyReqDto requestDto) {
        LimitInquriyResDto responseDto = new LimitInquriyResDto();
        try {
            // 요청 파라미터 세팅

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, requestDto.getMpaId(), konaCardLimitInquiryUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, LimitInquriyResDto.class);

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

    /**
     * 카드 소득공제 신청
     * */
    public CardDeductionResDto applyDeduction(CardDeductionReqDto requestDto) {
        CardDeductionResDto responseDto = new CardDeductionResDto();
        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();     // ID별 고유 MpaId 값

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaCardDeductionApplyUrl);

            // 요청 후 처리
            if (isNotBlank(strRtn)) {
                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CardDeductionResDto.class);

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
