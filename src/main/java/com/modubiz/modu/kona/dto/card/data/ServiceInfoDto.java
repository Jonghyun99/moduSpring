package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 카드상품정보
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceInfoDto {
    String serviceId;   // 상품 ID
    String serviceName; // 상품 명
    String serviceImage;    // 상품이미지 URL
}
