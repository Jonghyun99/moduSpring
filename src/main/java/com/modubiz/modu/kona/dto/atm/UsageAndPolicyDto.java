package com.modubiz.modu.kona.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 환불 사용 내용 및 정책 정보
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsageAndPolicyDto {

    private String refundMonthlyCount;          // 월 환불 횟수

    private String refundMonthlyLimitCount;     // 월 환불 제한 횟수

    private String refundMonthlyAmount;         // 월 환불 금액

    private String refundMonthlyLimitAmount;    // 월 환불 제한

    private String conditionOverPolicy;         // 환불 조건 초과시 처리 구분(D:환불 불가, C:수수료 부과)

    private String callCentermaxAmtMonth;       // 월간 콜센터 환불 한도

}
