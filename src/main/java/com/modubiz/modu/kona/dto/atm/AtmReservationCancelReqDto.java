package com.modubiz.modu.kona.dto.atm;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ATM 출금예약 취소 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmReservationCancelReqDto {

    @NotBlank(message = "9999")
    private String userId;

    @NotBlank(message = "9999")
    @Size(min = 27, max = 27)
    private String par;

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리

}
