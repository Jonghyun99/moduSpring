package com.modubiz.modu.kona.dto.user;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 회원가입 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

    @NotBlank(message = "9999")
    @NumberFormat
    private String userId;                  // 회원 Id

    @Size(min = 6, max = 6, message = "9999")
    @NotBlank(message = "9999")
    private String currentPassword;         // 현재 패스워드

    @Size(min = 6, max = 6, message = "9999")
    private String newPassword;             // 현재 패스워드

    @Size(min = 1, max = 100, message = "9999")
    private String userName;                // 성명

    @Size(min = 10, max = 20, message = "9999")
    private String mobileNumber;            // mobileNumber

    private AddressInfoDto addressInfo; // 주소정보

}
