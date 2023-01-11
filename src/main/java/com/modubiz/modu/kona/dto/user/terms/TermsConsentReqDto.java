package com.modubiz.modu.kona.dto.user.terms;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 약관동의 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermsConsentReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;               // 코나 MpaId 헤더로 처리

    @NotBlank(message = "9999")
    private String userId;

    private List<TcListDto> tcList;      // 약관 동의 목록

    /**
     * 약관 동의 목록
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TcListDto {

        @NotBlank(message = "9999")
        @NumberFormat
        private String tcId;            // 약관 ID

        private boolean acceptedByUser;  // 동의 여부
    }
}
