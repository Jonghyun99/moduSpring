package com.modubiz.modu.kona.dto.user;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 회원 기명화 신청 응답
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamedApplyResDto {

    private CommonResponseDto response;
}
