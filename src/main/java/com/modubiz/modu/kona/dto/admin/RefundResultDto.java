package com.modubiz.modu.kona.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 코나 어드민 충전
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundResultDto {

    @NotBlank(message = "9999")
    @Size(min = 25, max = 30, message = "9999")
    private String par;    // 카드 대체 번호

    private String transactionId;        // 카드 상품 ID

    @NumberFormat
    @Size(max = 15, message = "9999")
    private String amount;          // 충전 요청 금액

    @NotBlank(message = "9999")
    private String reason;        // 카드 상품 ID

}