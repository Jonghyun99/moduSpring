package com.modubiz.modu.kona.dto.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 카드배송 상태 조회
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDeliveryInquiryResDto {

    @NotBlank(message = "9999")
    private String status;   //상태    (READY, RECEIPT, PRE_SHIPPING, SHIPPING, ARRIVAL, LOST, RETURN, CANCEL, ETC)

    @NotEmpty(message = "9999")
    private CommonResponseDto response;

}
