package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 카드 포인트 정보
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardPointInfoDto {

    private String pointPolicyId;   // 포인트 정책 ID

    private String pointName;       // 포인트 명

    private String remainingPoint;  // 잔여 포인트

    private String autoUserYn;      // 자동사용 여부

}
