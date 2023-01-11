package com.modubiz.modu.kona.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 회원가입 V2 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationReqDto {

    @Size(min = 1, max = 80, message = "9999")
    @NotBlank(message = "9999")
    private String loginId;

    @Size(min = 6, max = 6, message = "9999")
    @NotBlank(message = "9999")
    private String loginPassword;          // 코나 로그인 패스워드

    @Size(min = 88, max = 88, message = "9999")
    @NotBlank(message = "9999")
    private String ci;                     // 회원의 CI 정보

    @Size(min = 1, max = 100, message = "9999")
    @NotBlank(message = "9999")
    private String userName;               // 성명

    @Size(min=8,max=8,message = "9999")
    @NotBlank(message = "9999")
    private String birthDate;              // birthdate (yyyymmdd로 요청받아 yyyy-mm-dd로 변환)

    @Size(max = 6, message = "9999")
    @NotBlank(message = "9999")
    private String gender;                 // 성별 (Male / Female)

    @Size(min = 10, max = 20, message = "9999")
    @NotBlank(message = "9999")
    private String mobileNumber;                // mobileNumber

    @Builder.Default
    @Size(min = 3, max = 9, message = "9999")
    private String nationality = "Korean"; // 국적 (Korean / Foreigner)

    private AddressInfoDto addressInfo; // 주소정보

    /* modu 서비스처리 시 필요한 필드 */
//    @UserExclude
//    @Size(min = 1, max = 1, message = "9999")
//    private String memberCl;         // 회원 구분 (일반 / 제휴)
//
//    @UserExclude
//    @Email
//    private String email;       // 이메일
//
//    @UserExclude
//    @Size(min = 2, max = 3, message = "9999")
//    @NotBlank(message = "9999")
//    private String country;     // 국가 2자리 or 3자리 코드
//
//    @UserExclude
//    @Size(min = 6, max = 20, message = "9999")
//    private String pw;    // 모두의비상금 로그인 패스워드
}
