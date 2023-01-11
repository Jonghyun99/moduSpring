package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 페이징 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponseDto {

    private String page;                // 페이징 인덱스
    private String totalPages;          // 전체 페이지 수
    private String numberOfElements;    // 전체 개수
}
