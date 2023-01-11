package com.modubiz.modu.kona.dto.card.data;

import com.modubiz.modu.kona.dto.user.AddressInfoDto;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

/**
 * 카드신청 유저 정보
 * */
public class UserInfoDto {


    @NumberFormat
    @NotBlank(message = "9999")
    private String userId;      // 회원 ID, 비회원 신청 시 :00000000

    private String userName;    // 실명 (id 00000000일 경우 필수)

    private String mobile;      // 전화번호 (id 00000000일 경우 필수)

    private String socialNo;    // 생년월일 8자리 (id 00000000일 경우 필수)

    private String gender;      // 성별 Male/Female (id 00000000일 경우 필수)

    private boolean isKorean;   // 한국인 여부 true:한국인, false:외국인 (id 00000000일 경우 필수)

    private AddressInfoDto addressInfoDto;  // 카드 수령지

}
