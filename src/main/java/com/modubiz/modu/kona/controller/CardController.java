package com.modubiz.modu.kona.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modubiz.modu.kona.dto.card.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.service.CardService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/kona/card")
public class CardController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CardService cardService;

    /**
     * 카드 등록 API
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카드등록",response = CardRegisterResDto.class)
    public Object cardRegister(@RequestBody @Valid CardRegisterReqDto requestDto, BindingResult bindingResult) throws Exception {

        CardRegisterResDto responseDto = new CardRegisterResDto();

        try {
            // 요청
            responseDto = cardService.registerCard(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 카드 배송조회 API
     */
    @PostMapping(value = "/delivery/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카드 배송조회",response = CardDeliveryInquiryResDto.class)
    public Object cardDeliveryInquiry(@RequestBody @Valid CardDeliveryInquiryReqDto requestDto, BindingResult bindingResult) throws Exception {

        CardDeliveryInquiryResDto responseDto = new CardDeliveryInquiryResDto();

        try {
            // 요청
            responseDto = cardService.inquiryDeliveryCard(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 보유카드 리스트 조회
     * */
    @PostMapping(value = "/list/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "보유카드리스트조회",response = CardListInquriyResDto.class)
    public Object cardListInquiry(@RequestBody @Valid CardListInquriyReqDto requestDto, BindingResult bindingResult) throws Exception {

        CardListInquriyResDto responseDto = new CardListInquriyResDto();

        try {
            // 요청
            responseDto = cardService.inquiryCardList(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 보유카드 한도 조회
     * */
    @PostMapping(value = "/limit/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "카드한도조회",response = LimitInquriyResDto.class)
    public Object cardLimitInquiry(@RequestBody @Valid LimitInquriyReqDto requestDto, BindingResult bindingResult) throws Exception {

        LimitInquriyResDto responseDto = new LimitInquriyResDto();

        try {
            // 요청
            responseDto = cardService.inquiryCardLimit(requestDto);

        } catch(Exception e) {
            responseDto.setCardInfoCount("0");
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 소득 공제 신청
     */
    @PostMapping(value = "/deduction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "소득공제신청",response = CardDeductionResDto.class)
    public Object deduction(@RequestBody @Valid CardDeductionReqDto requestDto, BindingResult bindingResult) throws Exception {

        CardDeductionResDto responseDto = new CardDeductionResDto();

        try {
            // 요청
            responseDto = cardService.applyDeduction(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 카드 사용내역 API
     */
    @ApiOperation(value = "카드거래내역조회",response = CardTransactionInquiryResDto.class)
    @PostMapping(value = "/transaction/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object cardTransactionInquiry(@RequestBody @Valid CardTransactionInquiryReqDto requestDto, BindingResult bindingResult) throws Exception {

        CardTransactionInquiryResDto responseDto = new CardTransactionInquiryResDto();

        try {
            // 요청
            responseDto = cardService.inquiryTransactionCard(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }


}
