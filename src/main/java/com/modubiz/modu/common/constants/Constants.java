package com.modubiz.modu.common.constants;

public interface Constants {

    // KonaCard 응답 코드
    public static final String KONACARD_SUCCESS = "000_000"; // 성공
    public static final String KONACARD_UNKNOWN_REASON = "900_001"; // 정의되지 않은 내부 시스템 오류
    public static final String KONACARD_INVALID_INPUT_HEADER = "010_001"; // 요청 헤더 정보 검증 오류
    public static final String KONACARD_INVALID_INPUT_PARAMETER = "020_001"; // 요청 파라미터 검증 오류
    public static final String KONACARD_METHOD_NOT_ALLOW = "020_998"; // 요청 Method 오류"
}
