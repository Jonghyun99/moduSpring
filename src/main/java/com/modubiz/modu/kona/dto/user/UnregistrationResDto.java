package com.modubiz.modu.kona.dto.user;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


/**
 * 회원탈퇴
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnregistrationResDto {

    @NotEmpty(message = "9999")
    private CommonResponseDto response;
}