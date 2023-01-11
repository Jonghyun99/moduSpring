package com.modubiz.modu.kona.dto.callback;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


/**
 * 카드 활성화 알림 응답
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUnregistrationResDto {
    
    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}