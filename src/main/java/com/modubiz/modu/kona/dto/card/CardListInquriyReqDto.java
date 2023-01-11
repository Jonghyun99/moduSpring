package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 보유카드 리스트 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardListInquriyReqDto {

    @Size(max = 100, message = "9999")
    @NotBlank(message = "9999")
    String userId;      // 회원 ID

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
