package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.common.annotation.UserExclude;
import com.modubiz.modu.kona.dto.card.data.ExpiryDateInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 카드 등록 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRegisterReqDto {

    @Size(max = 100, message = "9999")
    @NotBlank(message = "9999")
    private String userId;

    @Size(min = 16, max = 16, message = "9999")
    @NotBlank(message = "9999")
    private String physicalCardNo;       // 실물 카드번호

    private ExpiryDateInfoDto expiryDateInfo;    // 카드 유효기간

    @Size(min = 3, max = 3, message = "9999")
    @NotBlank(message = "9999")
    private String cvc;    // 카드 cvc

    @Size(min = 22, max = 22, message = "9999")
    private String cardApplyNo;     // 카드 신청번호

    /* modu 서비스처리 시 필요한 필드 */
//    @UserExclude
//    @Size(min = 4, max = 4, message = "9999")
//    private String atmPw;   // ATM 출금 비밃번호

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
