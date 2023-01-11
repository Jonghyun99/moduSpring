package com.modubiz.modu.kona.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * 카드 등록 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardRegisterResDto {

    private String cardProfile;        // 카드 프로파일? //TODO 뭔지 모름

    private String digitalCardNo;       // 모바일 카드번호 //TODO 용도 모름

    @NotBlank(message = "9999")
    private String par;         // 카드 대체번호

    @NotBlank(message = "9999")
    private String cardApplyNo;    // 카드 신청 번호

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
