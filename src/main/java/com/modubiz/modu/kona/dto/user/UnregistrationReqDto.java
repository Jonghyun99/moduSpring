package com.modubiz.modu.kona.dto.user;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 회원 탈퇴
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnregistrationReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

    @Size(max = 50, message = "9999")
    @NotBlank(message = "9999")
    private String userId;

}
