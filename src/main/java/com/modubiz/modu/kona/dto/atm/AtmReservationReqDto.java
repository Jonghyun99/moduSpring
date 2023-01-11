package com.modubiz.modu.kona.dto.atm;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ATM 출금 예약 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmReservationReqDto {

    @NotBlank(message = "9999")
    private String userId;                  // 유저 ID
    
    @Size(min = 16, max = 16)
    @NotBlank(message = "9999")
    private String oneTimeToken;                 // 일회용 Token

    @Size(min = 3, max = 3)
    @NotBlank(message = "9999")
    private String dcvv;        // 보안인증코드

    @NumberFormat
    @NotBlank(message = "9999")
    private String refundAmount;        // 환불 요청 금액

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
