package com.modubiz.modu.kona.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.card.data.CardInfoDto;
import com.modubiz.modu.kona.dto.card.data.LimitsDto;
import com.modubiz.modu.kona.dto.card.data.ServiceInfoDto;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 보유카드 리스트 조회
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitInquriyResDto {

    @NotBlank(message = "9999")
    private String cardInfoCount;      // 보유카드 개수

    private List<CardPaymentInfoDto> cardPaymentInfo;   // 카드조회정보

    private CommonResponseDto response;     // 응답


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CardPaymentInfoDto {   // 카드 데이터 정보

        private String par;    // 카드 대체 번호

        private String cardNo;

        private LimitsDto limits;       // 한도 정보
    }

}
