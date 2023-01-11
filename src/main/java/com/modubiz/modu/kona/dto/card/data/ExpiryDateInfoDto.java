package com.modubiz.modu.kona.dto.card.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 카드 유효기간 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpiryDateInfoDto {

    @Size(min = 2, max = 2, message = "9999")
    @NotBlank(message = "9999")
    private String yy;   // 유효기간(연)

    @Size(min = 2, max = 2, message = "9999")
    @NotBlank(message = "9999")
    private String mm;  // 유효기간(월)

}
