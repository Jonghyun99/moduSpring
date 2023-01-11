package com.modubiz.modu.kona.dto.admin;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ReversalResDto {
    @NotBlank(message = "9999")
    @Size(max = 100, message = "9999")
    private String transactionId;        // 카드 상품 ID

    @NotEmpty(message = "9999")
    private CommonResponseDto response;
}