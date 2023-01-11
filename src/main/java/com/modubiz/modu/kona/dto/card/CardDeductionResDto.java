package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 카드배송 상태 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDeductionResDto {

    private CommonResponseDto response;
}
