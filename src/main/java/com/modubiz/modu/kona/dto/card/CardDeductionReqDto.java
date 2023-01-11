package com.modubiz.modu.kona.dto.card;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 소득공제 신청
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDeductionReqDto {

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;

    @NotBlank(message = "9999")
    private String userId;

    @Size(min = 88, max = 88, message = "9999")
    @NotBlank(message = "9999")
    private String ci;

    @Size(min = 1, max = 100, message = "9999")
    @NotBlank(message = "9999")
    private String userName;
}
