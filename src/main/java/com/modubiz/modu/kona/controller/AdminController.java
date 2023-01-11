package com.modubiz.modu.kona.controller;

import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.admin.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.service.AdminService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/kona/admin")
public class AdminController implements CommonUtilIF {

    @Autowired
    AdminService adminService;

    /**
     * 어드민 충전
     */
    @PostMapping(value = "/recharge", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "어드민 충전",response = RechargeResDto.class)
    public Object recharge(@RequestBody @Valid RechargeReqDto requestDto, BindingResult bindingResult) throws Exception {
        RechargeResDto responseDto = new RechargeResDto();

        try {
            // 충전 요청
            responseDto = adminService.recharge(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    /**
     * 어드민 취소
     */
    @PostMapping(value = "/reversal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "어드민 취소(reversal)",response = ReversalResDto.class)
    public Object reversal(@RequestBody @Valid ReversalReqDto requestDto, BindingResult bindingResult) throws Exception {
        ReversalResDto responseDto = new ReversalResDto();

        try {
            // 충전 요청
            responseDto = adminService.reversal(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    /**
     * 어드민 환불(전체금액)
     */
    @PostMapping(value = "/refund", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "어드민 환불",response = RefundResDto.class)
    public Object refund(@RequestBody @Valid RefundReqDto requestDto, BindingResult bindingResult) throws Exception {
        RefundResDto responseDto = new RefundResDto();

        try {
            // 충전 요청
            responseDto = adminService.refund(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }


    /**
     * 어드민 환불(전체금액)
     */
    @PostMapping(value = "/refund/partial", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "어드민 부분환불",response = RefundResDto.class)
    public Object partialRefund(@RequestBody @Valid PartialRefundReqDto requestDto, BindingResult bindingResult) throws Exception {
        RefundResDto responseDto = new RefundResDto();

        try {
            // 충전 요청
            responseDto = adminService.partialRefund(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }
}
