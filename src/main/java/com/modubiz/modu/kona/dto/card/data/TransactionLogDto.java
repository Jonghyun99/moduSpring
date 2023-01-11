package com.modubiz.modu.kona.dto.card.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 거래로그
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionLogDto {
    
    @NotBlank(message = "9999")
    private String apporvalCode;    // 승인코드


    @NotBlank(message = "9999")
    private String paymentType;    // 거래타입


    @NotBlank(message = "9999")
    private String cardNo;    // 카드번호

    private String merchantName;    // 거래 가맹점명
    
    @NotBlank(message = "9999")
    @NumberFormat
    private String discountAmount;    // 할인금액 //TODO Number 자료형으로 오는 필드 int형으로 받을지 string으로 받을지..

    @NotBlank(message = "9999")
    @NumberFormat
    private String fee;    // 수수료
    
    @NotBlank(message = "9999")
    @NumberFormat
    private String trAmount;    // 이체금액

    @NotBlank(message = "9999")
    @NumberFormat
    private String userUsedPoint;    // 사용된 유저포인트

    @NotBlank(message = "9999")
    @NumberFormat
    private String cardUsezdPoint;    // 사용된 카드 포인트

    @NotBlank(message = "9999")
    @NumberFormat
    private String userSavedPoint;    // 적립된 유저 포인트

    @NotBlank(message = "9999")
    @NumberFormat
    private String cardSavedPoint;    // 적립된 카드 포인트

    private String approvalDateTime;    // 승인일시 (yyyyMMddHHmmss)

    private String reversalStatus;      // 취소 상태 (00: 취소 완료, 01:취소요청 없음, 02:부분취소 완료)

    @NotBlank(message = "9999")
    @NumberFormat
    private String orgAmount;       // 원거래 금액

    @NotBlank(message = "9999")
    @NumberFormat
    private String settleRate;      // 서버할인 시 할인율

    @NotBlank(message = "9999")
    private String cardType;        // 카드타입 (00: 모바일카드, 01:실물카드)

    private String rechargeCancellable; // 충전 취소 여부 (0:취소 불가, 1:취소 가능+ 충전형카드, 2: 취소 가능 + Disposable card)

    private String nrNumber;        // 거래 고유 번호

    private String couponId;        // 쿠폰 ID

    private String couponName;      // 쿠폰 명

    private String couponType;      // 쿠폰타입 (11:내부 무상, 12:내부 유상, 21:외부 무상, 22:외부 유상)

    private String cancelType;      // 거래취소 유형  (00: 전체취소, 01:부분취소)

    private String track2Data;      // Track2Data

    @NumberFormat
    @NotBlank(message = "9999")
    private String complexTrCount;  // 복합결제 내역 개수

    @NotEmpty(message = "9999")
    private ComplexTrInfoDto complexTrInfo; // 복합결제

    @NotEmpty(message = "9999")
    private PageResponseDto pageResponse;    // 페이징 DTO



}
