package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결제 계좌 정보
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankInfoDto {
    
    private String bankName;    // 은행명 (WELCOME, ACUON, LEMONTREE)
    
    private String accountNo;   // 은행 계좌 번호
}
