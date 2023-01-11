package com.modubiz.modu.kona.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.card.data.CardInfoDto;
import com.modubiz.modu.kona.dto.card.data.ServiceInfoDto;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 보유카드 리스트 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardListInquriyResDto {

    @NotBlank(message = "9999")
    private String cardDataCount;      // 보유카드 개수

    private List<CardDataInfoDto> cardDataInfo;   // 카드조회정보

    private CommonResponseDto response;     // 응답


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CardDataInfoDto {   // 카드 데이터 정보
        private ServiceInfoDto serviceInfo; // 카드 상품 정보
        private CardInfoDto cardInfo;       // 카드 정보
    }

}
