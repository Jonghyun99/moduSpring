package com.modubiz.modu.kona.dto.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 카드 활성화 알림 요청
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUnregistrationReqDto {

    private String userId;

    private String par;

    private String digitalCardNo;

    private String physicalCardNo;

    private String url;

}