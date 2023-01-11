package com.modubiz.modu.kona.dto.user;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 회원 기명화 신청
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamedApplyReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

    @NotBlank(message = "9999")
    @Size(max = 100, message = "9999")
    private String userId;               // 성명

    @NotBlank(message = "9999")
    @Size(min = 1, max = 1, message = "9999")
    private String isCheckNamedYn;    // 모두의비상금 로그인 패스워드
}
