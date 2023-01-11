package com.modubiz.modu.kona.dto.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/**
 * 코나 거래내역 알림
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionReqDto {

    @NotBlank(message = "9999")
    private String userId;

    @NotBlank(message = "9999")
    private String par;

    @NotBlank(message = "9999")
    private String trType;

    @NotBlank(message = "9999")
    private String authCancelType;

    private String orgAmount;

    private String trAmount;

    private String discountAmount;

    private String pointAmount;

    private String balanceAfter;

    private String approvalDateTime;

    private String merchantName;

    private String approvalCode;

    private String cardNo;

    private String mti;

    private String responseCode;

    private String url;
}