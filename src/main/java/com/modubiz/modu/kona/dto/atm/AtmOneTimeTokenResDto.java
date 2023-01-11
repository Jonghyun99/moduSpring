package com.modubiz.modu.kona.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * ATM 토큰 발급 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtmOneTimeTokenResDto {

    private String oneTimeToken;        // 일회용 Token

    private String dcvv;       // DVBB (Dynamic CVV 보안인증코드)

    @NotBlank(message = "9999")
    private String expiryTime;         // 발급된 Token 유효시간 (ms)

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
