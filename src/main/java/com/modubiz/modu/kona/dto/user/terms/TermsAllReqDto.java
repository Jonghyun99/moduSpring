package com.modubiz.modu.kona.dto.user.terms;

import com.modubiz.modu.common.annotation.UserExclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * KonaCard 약관조회 DTO
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermsAllReqDto {
    
    private String userId;                  //

    private String tcActionType;        // 약관 Action Type (default: null), PY: 개인정보 수집, UNDER_14: 만 14세 미만, COUPON_SHOP: 쿠폰샵, EZWEL: 복지몰, OPEN_BANK: 오픈뱅킹, OPEN_BANK_SPECIFIC: 오픈뱅킹 출금/조회, MKT_NOTI: 마케팅 수신, DEDUCT: 자동 소득 공제

    private String viewPositionType;    // 약관 노출 시점 구분 (OPEN_BANK: 오픈 뱅킹 서비스 이용 시점, USER_PORTAL_CARD: 카드관리사이트 이용 시점)

    private String locale;              // 국가 언어 (default:KO)

    @NotBlank(message = "9999")
    @UserExclude
    private String mpaId;           // 코나 MpaId 헤더로 처리
}
