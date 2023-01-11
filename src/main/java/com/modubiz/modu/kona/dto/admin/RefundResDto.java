package com.modubiz.modu.kona.dto.admin;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


/**
 * 코나 어드민 충전
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundResDto {

    @NotEmpty(message = "9999")
    private RefundResultDto result;

    @NotEmpty(message = "9999")
    private CommonResponseDto response;
}