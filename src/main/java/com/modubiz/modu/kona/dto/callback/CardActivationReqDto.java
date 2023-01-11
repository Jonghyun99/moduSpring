package com.modubiz.modu.kona.dto.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 카드 활성화 알림 요청
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardActivationReqDto {

    private String cardApplyNo;

    private String userId;

    private String par;

    private String physicalCardNo;

    private String receiverName;

    private String receiveDay;

    private String url;

}