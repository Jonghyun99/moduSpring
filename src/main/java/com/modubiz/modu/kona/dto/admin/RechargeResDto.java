package com.modubiz.modu.kona.dto.admin;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 코나 어드민 충전
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechargeResDto {

    @NotBlank(message = "9999")
    @Size(max = 50, message = "9999")
    private String userId;          // 회원 ID

    @NotBlank(message = "9999")
    @Size(max = 100, message = "9999")
    private String transactionId;        // 카드 상품 ID

    @NotBlank(message = "9999")
    @Size(min = 25, max = 30, message = "9999")
    private String par;    // 카드 대체 번호

    @NotEmpty(message = "9999")
    private CommonResponseDto response;
}