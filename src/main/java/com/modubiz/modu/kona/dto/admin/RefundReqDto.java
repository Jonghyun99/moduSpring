package com.modubiz.modu.kona.dto.admin;

import com.modubiz.modu.common.annotation.UserExclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 코나 어드민 충전
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundReqDto {
    @NotBlank(message = "9999")
    @Size(max = 50, message = "9999")
    @UserExclude
    private String mpaId;

    @NotBlank(message = "9999")
    @Size(min = 27, max = 27, message = "9999")
    private String par;    // 카드 대체 번호

    @ApiModelProperty(hidden = true)
    private String rechargerId;    //충전상 ID

}