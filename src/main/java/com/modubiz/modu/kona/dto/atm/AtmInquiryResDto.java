package com.modubiz.modu.kona.dto.atm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


/**
 * ATM 출금 금액 조회 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtmInquiryResDto {

    private String userId;        // 회원 Id

    private String par;       // 카드 대체 번호

    private String refundFeeRate;               // 수수료율

    private String balance;                     // 카드 잔액

    private String refundCheckBalance;          // 순수 결제만 반영한 잔액

    private String discountAmount;              // 할인 받은 금액

    private String refundFee;                   // 환불 수수료

    private String refundAmount;                // 환불 가능 금액

    private String expiredFee;                  // 만료 수수료

    private String refundable;                  // 최중 환불 가능 여부

    private String refundThresholdBalance;      // 환불 임계치 잔액 (환불 가능해지는 잔액, 이 이하 떨어져야 가능)

    private String refundThresholdRatio;        // 환불 임계치 비율 (이 이상 사용하여야 환불 가능)

    private String currentBalanceRatio;         // 현재 잔액 사용율

    private String lastLoadedBalance;           // 마지막 충전 잔액

    private String lastLoadedDateTime;          // 최종 충전 날짜시간

    private String atmRefundAmount;             // ATM 출금 금액

    private UsageAndPolicyDto usageAndPolicy;   // 환불 사용 내용 및 정책 정보

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
