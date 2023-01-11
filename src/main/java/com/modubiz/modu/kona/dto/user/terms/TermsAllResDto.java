package com.modubiz.modu.kona.dto.user.terms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 약관조회 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TermsAllResDto {

    private List<TermsListDto> termsList;
    private CommonResponseDto response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TermsListDto{

        @NotBlank(message = "9999")
        private String tcId;    //약관 ID

        private String tcActionType;    // 약관 Action Type

        @NotBlank(message = "9999")
        private String title;       // 약관 명

        @NotBlank(message = "9999")
        private String url;       // 약관URL

        private String descriptionUrl;       // 약관 설명 URL

        @NotBlank(message = "9999")
        private String required;       // 약관 필수 구분

        @NotBlank(message = "9999")
        private String order;       // 약관 순서

        @NotBlank(message = "9999")
        private String isAgree;       // 회원 동의 여부

        private String agreeDate;       // 회원 약관 동의 일자

        private String viewPositionType;       // 약관 노출 시점 구분

    }
}
