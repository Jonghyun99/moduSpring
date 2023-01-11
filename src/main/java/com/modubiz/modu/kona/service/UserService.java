package com.modubiz.modu.kona.service;

import com.modubiz.modu.common.constants.Constants;
import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.dto.common.CommonResponseWrapper;
import com.modubiz.modu.kona.dto.user.*;
import com.modubiz.modu.kona.dto.user.terms.TermsAllReqDto;
import com.modubiz.modu.kona.dto.user.terms.TermsAllResDto;
import com.modubiz.modu.kona.dto.user.terms.TermsConsentReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class UserService extends CommonService implements CommonUtilIF {

    @Value("${kona.user.registration.url}")
    private String konaUserRegistrationUrl;

    @Value("${kona.user.update.url}")
    private String konaUserUpdateUrl;

    @Value("${kona.user.unregistration.url}")
    private String unregistrationUrl;

    @Value("${kona.user.terms.all.url}")
    private String konaUserTermsAllUrl;

    @Value("${kona.user.terms.check}")
    private String konaUserCheckTermsUrl;

    @Value("${kona.user.named.url}")
    private String konaUserNamedUrl;

    @Value("${kona.user.named.validate.url}")
    private String konaUserNamedValidateUrl;

    /**
     * 회원가입 서비스
     */
    public UserRegistrationResDto registrationUser(UserRegistrationReqDto requestDto) {
        UserRegistrationResDto responseDto = new UserRegistrationResDto();

        try {
            // 요청 파라미터 세팅 및 매핑 (DTO 어노테이션으로 처리 고려하기)
            String mpaId = "";      // ID별 고유 MpaId 값 (회원가입 시 공백으로 요청)

            // 성별 매핑
            switch(requestDto.getGender().toUpperCase(Locale.ROOT)) {
                case "M":
                    requestDto.setGender("Male");
                case "F":
                    requestDto.setGender("Female");
            }

            // 국적 매핑
            if(isEqual(requestDto.getNationality().toUpperCase(Locale.ROOT),"KOR")){
                requestDto.setNationality("Korean");
            } else {
                requestDto.setNationality("Foreigner");
            }

            // 생일 매핑 (yyyyMMdd -> yyyy-MM-dd)
            if(!requestDto.getBirthDate().contains("-")) {
                StringBuffer birthDate = new StringBuffer(requestDto.getBirthDate());
                birthDate.insert(4, "-");
                birthDate.insert(7, "-");
                requestDto.setBirthDate(birthDate.toString());
            }

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserRegistrationUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, UserRegistrationResDto.class);

                // 성공시 처리
                if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //충전 성공 DB처리

                } else {
                    //충전 실패 DB 처리
                }
                // 응답값 못받은 경우?
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 회원 정보 변경
     */
    public UpdateResDto updateUser(UpdateReqDto requestDto) {
        UpdateResDto responseDto = new UpdateResDto();

        try {
            // 요청 파라미터 세팅 및 매핑 (DTO 어노테이션으로 처리 고려하기)
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값 (회원가입 시 공백으로 요청)


            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserUpdateUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, UpdateResDto.class);

            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }
    /**
     * 회원탈퇴
     */
    public UnregistrationResDto unregistrationUser(UnregistrationReqDto requestDto) {
        UnregistrationResDto responseDto = new UnregistrationResDto();

        try {
            // 요청 파라미터 세팅 및 매핑 (DTO 어노테이션으로 처리 고려하기)
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값 (회원가입 시 공백으로 요청)


            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, unregistrationUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, UnregistrationResDto.class);

            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }


    /**
     * 약관조회 서비스 (회원가입 시에만 사용)
     */
    public TermsAllResDto getTerms(TermsAllReqDto requestDto) {
        TermsAllResDto responseDto = new TermsAllResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();            // ID별 고유 MpaId 값
//            requestDto.setViewPositionType("USER_PORTAL_CARD");  // 약관 노출 시점??
            requestDto.setLocale("KO");

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserTermsAllUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, TermsAllResDto.class);

                // 성공시 처리
                if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    // 성공 DB처리

                } else {
                    // 실패 DB 처리
                }
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }


    /**
     * KonaCard 약관동의 서비스
     */
    public CommonResponseWrapper consentTerms(TermsConsentReqDto requestDto) {
        CommonResponseWrapper responseDto = new CommonResponseWrapper();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();            // ID별 고유 MpaId 값

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserCheckTermsUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, CommonResponseWrapper.class);

                // 성공시 처리
                if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //충전 성공 DB처리

                } else {
                    //충전 실패 DB 처리

                }
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch (Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }


    /**
     * 기명화
     */
    public NamedApplyResDto named(NamedApplyReqDto requestDto) {
        NamedApplyResDto responseDto = new NamedApplyResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserNamedUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {

                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, NamedApplyResDto.class);

                // 성공시 처리
                if(isEqual(responseDto.getResponse().getCode(), Constants.KONACARD_SUCCESS)) {
                    //기명화 성공 DB처리


                }
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 계좌 예금주 실명 검증
     */
    public AccountValidationResDto namedValidate(AccountValidationReqDto requestDto) {
        AccountValidationResDto responseDto = new AccountValidationResDto();

        try {
            // 요청 파라미터 세팅
            String mpaId = requestDto.getMpaId();      // ID별 고유 MpaId 값

            // 코나카드로 요청
            String strRtn = requestToKonaCard(requestDto, mpaId, konaUserNamedValidateUrl);

            // 요청 후 처리
            if(isNotBlank(strRtn)) {
                // 응답 값 convert
                responseDto = mapper.readValue(strRtn, AccountValidationResDto.class);
            } else {
                responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            }
        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }
}
