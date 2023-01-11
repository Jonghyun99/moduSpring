package com.modubiz.modu.kona.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


/**
 * ATM 출금 예약 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtmReservationResDto {

    private String requestTime;        // 예약 요청 시각

    private String aspId;

    private String userId;          // 회원 ID

    private String par;             // 카드 대체 번호

    private String balance;         // 잔액

    private String fee;             //

    private String reservedAmount;  // 예약된 환불 요청 금액

    private String otp;             // 채번된 One Time Password (8자리)

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
