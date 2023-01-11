package com.modubiz.modu.kona.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.common.annotation.UserExclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * 회원가입 V2 DTO
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResDto {

    @NotBlank(message = "9999")
    @NumberFormat
    private String userId;      // 회원 Id

    @UserExclude
    @NotBlank(message = "9999")
    private String mpaId;   // App에 부여하는 고유 식별자

    @NotEmpty(message = "9999")
    private CommonResponseDto response;
}