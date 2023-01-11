package com.modubiz.modu.kona.dto.atm;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ATM 토큰 발급 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmOneTimeTokenReqDto {

    @Size(min = 16, max = 16, message = "9999")
    @NotBlank(message = "9999")
    private String cardNo;                  // 카드번호

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
