package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.kona.dto.card.data.PageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 카드 거래내역 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardTransactionInquiryReqDto {
    @NotBlank(message = "9999")
    @Size(max = 50, message = "9999")
    private String mpaId;

    private String userId;        // 회원 ID

    @Size(min = 27, max = 27, message = "9999")
    @NotBlank(message = "9999")
    private String par;         // 카드 대체 번호

    @Size(min = 8, max = 8, message = "9999")
    @NotBlank(message = "9999")
    private String startDate;   // 기간검색 시작일(yyyyMMdd)

    @Size(min = 8, max = 8, message = "9999")
    @NotBlank(message = "9999")
    private String endDate;   // 기간검색 종료일(yyyyMMdd)

    @Size(min = 18, max = 18, message = "9999")
    private String nrNumber;    // 거래 고유 번호? //TODO 확인 필요

    private PageRequestDto pageRequest;

}
