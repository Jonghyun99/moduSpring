package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

/**
 * 결제 한도 정보
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitsDto {
    
    @NumberFormat
    private String month;   // 월 결제 한도 금액

    @NumberFormat
    private String day;     // 일 결제 한도 금액

    @NumberFormat
    private String once;    // 1회 결제 한도 금액

}
