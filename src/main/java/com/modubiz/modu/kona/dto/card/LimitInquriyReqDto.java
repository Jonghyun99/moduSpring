package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 카드 한도 조회
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitInquriyReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

    @Size(max = 100, message = "9999")
    @NotBlank(message = "9999")
    String userId;      // 회원 ID

    @NotBlank(message = "9999")
    @Size(min = 25, max = 30, message = "9999")
    private String par;    // 카드 대체 번호

    @Size(min = 16, max = 16, message = "9999")
    @NotBlank(message = "9999")
    private String cardNo;
}
