package com.modubiz.modu.kona.dto.card.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * 페이지 요청 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {

    @Size(max = 1000, message = "9999")
    private String page;

    @Size(max = 10000, message = "9999")
    private String pageSize;    //페이징 크기 (기본값 = 10, All = 0)

    @Size(min = 4, max = 4, message = "9999")
    private String orderByDirection;    // 정렬순서, 기본값 = ASC
}
