package com.modubiz.modu.kona.dto.atm;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ATM 출금 금액 조회 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmInquiryReqDto {

    @NotBlank(message = "9999")
    private String userId;
    
    @Size(min = 27, max = 27)
    @NotBlank(message = "9999")
    private String par;                 // 카드 대체 번호

    @NotBlank(message = "9999")
    @NumberFormat
    private String refundAmount;        // 환불 요청 금액

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
