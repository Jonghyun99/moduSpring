package com.modubiz.modu.kona.dto.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 코나 어드민 충전
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaystoryVacntReqDto {

    private String vacntNo;

    @NotBlank(message = "9999")
    @Size(min = 30, max = 30, message = "9999")
    private String tid;

    @NotBlank(message = "9999")
    @Size(min = 10, max = 10, message = "9999")
    private String mid;

    @Size(max = 40, message = "9999")
    private String ordNo;

    @NumberFormat
    @NotBlank(message = "9999")
    @Size(max = 15, message = "9999")
    private String amt;

    @Size(max = 100, message = "9999")
    private String goodsName;

    private String cancelYN;

    private String appDtm;

    private String fnNm;

    private String cpCd;

    @NumberFormat
    @NotBlank(message = "9999")
    @Size(max = 15, message = "9999")
    private String ediDate;

    @NumberFormat
    @NotBlank(message = "9999")
    private String hashStr;

    @NotBlank(message = "9999")
    @Size(max = 4, message = "9999")
    private String resultCd;

    @NotBlank(message = "9999")
    @Size(max = 256, message = "9999")
    private String resultMsg;

}