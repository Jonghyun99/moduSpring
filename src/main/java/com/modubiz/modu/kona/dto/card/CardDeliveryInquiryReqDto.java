package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 카드배송 상태 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDeliveryInquiryReqDto {

    @Size(min = 22, max = 22, message = "9999")
    @NotBlank(message = "9999")
    private String cardApplyNo;    // 카드 신청번호

    @Size(max = 100, message = "9999")
    @NotBlank(message = "9999")
    private String userId;    // 회원 Id (숫자)

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

}
