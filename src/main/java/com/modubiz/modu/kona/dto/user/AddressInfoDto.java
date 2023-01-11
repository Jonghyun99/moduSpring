package com.modubiz.modu.kona.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 주소정보
 * */
@Data
public class AddressInfoDto {

    @NotBlank(message = "9999")
    @Size(min = 5, max = 6, message = "9999")
    private String zipCode;

    @NotBlank(message = "9999")
    @Size(min = 1, max = 255, message = "9999")
    private String address;

    @NotBlank(message = "9999")
    @Size(min = 1, max = 255, message = "9999")
    private String detail;

}
