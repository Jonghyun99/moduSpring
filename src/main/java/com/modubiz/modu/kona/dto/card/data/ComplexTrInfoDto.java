package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

/**
 * 복합결제정보
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexTrInfoDto {

    @NotBlank(message = "9999")
    private String priority;    // 복합결제 우선순위

    private String pointPolicytype; // 포인트 정책 타입

    private String pointPolicyId;   // 포인트 정책 ID

    private String pointPolicyName; // 포인트 정책명

    @NumberFormat
    @NotBlank(message = "9999")
    private String trAmount;    // 포인트 거래 금액

    @NumberFormat
    @NotBlank(message = "9999")
    private String amount;  // 사용 포인트

}
