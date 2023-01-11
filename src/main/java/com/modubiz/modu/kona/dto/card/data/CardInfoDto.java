package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 카드정보
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardInfoDto {

    //private String serviceId;   // 신청한 상품 Id (제휴사에서 발급한 카드 상품 ID)

    private String par;         // 카드 대체 번호

    private String physicalCardNo;  //실물카드 번호

    private String physicalCardStatus;  //실물카드 상태

    private String expiryDate;     // 카드 유효기간 (DD-MM-YY)

    private String digitalCardNo;   // 모바일카드 번호

    private String digitalCardStatus;   // 모바일 카드 상태

    private LimitsDto limits;       // 결제 한도 정보

    private String enabledFIC;      // 현금 IC 포함 여부 (true: 포함, false: 미포함)

    private String cardApplyNo;     // 카드 신청 번호

    private String cardPointCount;  // 포인트정보 개수

    private List<CardPointInfoDto> cardPointInfo; // 카드 포인트 정보

    private BankInfoDto bankInfo;   // 결제 계좌 정보

    private String balance;
}
