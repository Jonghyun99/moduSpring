package com.modubiz.modu.kona.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modubiz.modu.kona.dto.atm.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.dto.common.CommonResponseWrapper;
import com.modubiz.modu.kona.service.AtmService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/kona/atm")
public class AtmController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AtmService atmService;

    /**
     * ATM 출금 일회용 Token 발급
     */
    @PostMapping(value = "/onetimetoken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "출금일회용 Token 발급",response = AtmOneTimeTokenResDto.class)
    public Object oneTimeTokenIssue(@RequestBody @Valid AtmOneTimeTokenReqDto requestDto, BindingResult bindingResult) throws Exception {

        AtmOneTimeTokenResDto responseDto = new AtmOneTimeTokenResDto();

        try {
            // 요청
            responseDto = atmService.issueOneTimeToken(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * ATM 출금 금액 조회
     */
    @PostMapping(value = "/refund/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "ATM 출금 금액 조회",response = AtmInquiryResDto.class)
    public Object RefundAmountInquiry(@RequestBody @Valid AtmInquiryReqDto requestDto, BindingResult bindingResult) throws Exception {

        AtmInquiryResDto responseDto = new AtmInquiryResDto();

        try {
            // 요청
            responseDto = atmService.inquiryRefundAmount(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * ATM 출금 예약
     * */
    @PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "ATM 출금 예약",response = AtmReservationResDto.class)
    public Object RefundReservation(@RequestBody @Valid AtmReservationReqDto requestDto, BindingResult bindingResult) throws Exception {

        AtmReservationResDto responseDto = new AtmReservationResDto();

        try {
            // 요청
            responseDto = atmService.reservationRefund(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * ATM 출금 예약 취소
     */
    @PostMapping(value = "/reservation/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "ATM 출금 예약 취소",response = CommonResponseWrapper.class)
    public Object RefundReservationCancel(@RequestBody @Valid AtmReservationCancelReqDto requestDto, BindingResult bindingResult) throws Exception {

        CommonResponseWrapper responseDto = new CommonResponseWrapper();

        try {
            // 요청
            responseDto = atmService.cancelRefundReservation(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

}
