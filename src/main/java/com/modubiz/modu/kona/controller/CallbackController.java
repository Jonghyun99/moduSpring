package com.modubiz.modu.kona.controller;

import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.common.util.EncryptUtil;
import com.modubiz.modu.kona.dto.admin.RechargeReqDto;
import com.modubiz.modu.kona.dto.admin.RechargeResDto;
import com.modubiz.modu.kona.dto.admin.ReversalReqDto;
import com.modubiz.modu.kona.dto.admin.ReversalResDto;
import com.modubiz.modu.kona.dto.callback.*;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.service.AdminService;
import com.modubiz.modu.kona.service.CallbackService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/callback")
public class CallbackController implements CommonUtilIF {

    @Autowired
    AdminService adminService;

    @Autowired
    CallbackService callbackService;

    @Value("${paystory.mid.key}")
    String mKey;


    /**
     * 실시간 거래 알림
     */
    @PostMapping(value = "/kona/transaction/notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "코나 거래 내역 callback", response = CardTransactionResDto.class)
    public Object transaction(@RequestBody @Valid CardTransactionReqDto requestDto, BindingResult bindingResult) throws Exception {
        CardTransactionResDto responseDto = new CardTransactionResDto();

        try {
            // 거래 내역 DB 저장
            responseDto = callbackService.insertKonaCardTrans(requestDto);

        } catch(Exception e) {
            log.error("transaction", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    /**
     * 카드 활성화 알림
     */
    @PostMapping(value = "/kona/card/activate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "코나 카드 활성화 callback", response = CardActivationResDto.class)
    public Object activate(@RequestBody @Valid CardActivationReqDto requestDto, BindingResult bindingResult) throws Exception {
        CardActivationResDto responseDto = new CardActivationResDto();

        try {
            // 활성 요청
            responseDto = callbackService.insertKonaCardActive(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    /**
     * 배송 상태 알림
     */
    @PostMapping(value = "/kona/card/shipping", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "코나 카드 배송 상태 callback", response = RechargeResDto.class)
    public Object shipping(@RequestBody @Valid RechargeReqDto requestDto, BindingResult bindingResult) throws Exception {
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
     * 탈퇴 회원 알림
     */
    @PostMapping(value = "/kona/user/unregister", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "코나 탈퇴 회원 callback", response = UserUnregistrationResDto.class)
    public Object unregister(@RequestBody @Valid UserUnregistrationReqDto requestDto, BindingResult bindingResult) throws Exception {
        UserUnregistrationResDto responseDto = new UserUnregistrationResDto();

        try {
            // 충전 요청
            responseDto = callbackService.unregisterUser(requestDto);

        } catch(Exception e) {
            log.error("recharge", e);
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
        }

        return responseDto;
    }

    /**
     * 가상 계좌 입금 통보
     */
    @PostMapping(value = "/paystory/vacnt/transaction", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "페이스토리 가상계좌 입금 callback")
    public void paystoryVacntCallback(@Valid PaystoryVacntReqDto requestDto, BindingResult bindingResult) throws Exception {

        try {
            //requestDto 검증(hash값 검증)
            String hashStr = requestDto.getHashStr();
            String mid = requestDto.getMid();
            String ediDate = requestDto.getEdiDate();
            String amt = requestDto.getAmt();

            String genStr = EncryptUtil.getSHA256(mid + ediDate + amt + mKey);

            if(isNotEqual(hashStr, genStr)) {
                log.warn("hash value mismatch!!");
                log.warn("generated hash:{}", genStr);
                log.warn("received hash :{}", hashStr);
                return;
            }

            //충전 전 DB 처리
            RechargeReqDto reqDto = callbackService.insertChargingRequest(requestDto);

            if(isNotEmpty(reqDto)) {
                //충전
                RechargeResDto resDto = adminService.recharge(reqDto);


                if(isNotEmpty(resDto)) {
                    //결과 업데이트
                    if(!callbackService.updateChargingRequest(requestDto, resDto)) {
                        log.error("충전 실패!!!!!!!!!");
                        log.error("PaystoryVacntReqDto={}", requestDto);
                        log.error("AdminRechargeReqDto={}", reqDto);
                        log.error("AdminRechargeResDto={}", resDto);

                        // 충전 취소 처리
                        ReversalReqDto reversalReqDto = new ReversalReqDto();
                        reversalReqDto.setAmount(reqDto.getAmount());
                        reversalReqDto.setPar(reqDto.getPar());
                        reversalReqDto.setTransactionId(resDto.getTransactionId());
                        ReversalResDto reversalResDto = adminService.reversal(reversalReqDto);

                        //실패 상태로 DB 업데이트 처리(?)
                    }
                } else {
                    //충전 응답 수신 오류
                }
            }
        } catch(Exception e) {
            log.error("paystoryVacntCallback", e);
        }

    }
}
