package com.modubiz.modu.kona.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.card.data.TransactionLogDto;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 카드 거래내역 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardTransactionInquiryResDto {

    @NotBlank(message = "9999")
    @NumberFormat
    private String logCount;        // 거래내역 개수

    @NotEmpty(message = "9999")
    private TransactionLogDto transactionLog;   // 거래로그

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
