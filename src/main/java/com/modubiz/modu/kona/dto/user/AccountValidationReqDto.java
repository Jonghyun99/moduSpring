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
public class AccountValidationReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

    @NotBlank(message = "9999")
    @Size(max = 100, message = "9999")
    private String userId;

    @Size(min = 1, max = 20, message = "9999")
    @NotBlank(message = "9999")
    private String bankAccount;

    @Size(min = 3, max = 3, message = "9999")
    @NotBlank(message = "9999")
    private String bankCode;

    @Size(min = 6, max = 6, message = "9999")
    @NotBlank(message = "9999")
    private String personalNumber;

    @Size(min = 1, max = 100, message = "9999")
    private String name;
}
