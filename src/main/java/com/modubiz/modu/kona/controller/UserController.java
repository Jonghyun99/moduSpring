package com.modubiz.modu.kona.controller;

import com.modubiz.modu.common.util.CommonUtilIF;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.dto.common.CommonResponseWrapper;
import com.modubiz.modu.kona.dto.user.*;
import com.modubiz.modu.kona.dto.user.terms.TermsAllReqDto;
import com.modubiz.modu.kona.dto.user.terms.TermsAllResDto;
import com.modubiz.modu.kona.dto.user.terms.TermsConsentReqDto;
import com.modubiz.modu.kona.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/kona/user")
public class UserController implements CommonUtilIF {

    @Autowired
    UserService userService;

    /**
     * 회원가입 V2 API 요청
     */
    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "회원가입",response = UserRegistrationResDto.class)
    public Object userRegistration(@RequestBody @Valid UserRegistrationReqDto requestDto, BindingResult bindingResult) throws Exception {

        UserRegistrationResDto responseDto = new UserRegistrationResDto();

        try {
            responseDto = userService.registrationUser(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 회원 정보 변경
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "회원 정보 변경",response = UpdateResDto.class)
    public Object update(@RequestBody @Valid UpdateReqDto requestDto, BindingResult bindingResult) throws Exception {

        UpdateResDto responseDto = new UpdateResDto();

        try {
            responseDto = userService.updateUser(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 회원탈퇴
     */
    @PostMapping(value = "/unregistration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "회원탈퇴",response = UnregistrationResDto.class)
    public Object userUnregistration(@RequestBody @Valid UnregistrationReqDto requestDto, BindingResult bindingResult) throws Exception {

        UnregistrationResDto responseDto = new UnregistrationResDto();

        try {
            responseDto = userService.unregistrationUser(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 약관조회 요청 (회원가입 시에만 사용)
     */
    @PostMapping(value = "/terms/inquiry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "약관조회(회원가입)",response = TermsAllResDto.class)
    public Object inquiryTerms(@RequestBody @Valid TermsAllReqDto requestDto, BindingResult bindingResult) throws Exception {
        TermsAllResDto responseDto = new TermsAllResDto();

        try {
            // 약관조회 요청
            responseDto = userService.getTerms(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }

        return responseDto;
    }

    /**
     * 약관동의 요청
     */
    @PostMapping(value = "/terms/consent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "약관동의",response = CommonResponseWrapper.class)
    public Object consentTerms(@RequestBody @Valid TermsConsentReqDto requestDto, BindingResult bindingResult) throws Exception {
        CommonResponseWrapper responseDto = new CommonResponseWrapper();

        try {
            // 약관동의 요청
            responseDto = userService.consentTerms(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 기명화 신청
     */
    @ApiOperation(value = "기명화 신청",response = NamedApplyResDto.class)
    @PostMapping(value = "/named/apply", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object named(@RequestBody @Valid NamedApplyReqDto requestDto, BindingResult bindingResult) throws Exception {
        NamedApplyResDto responseDto = new NamedApplyResDto();

        try {
            responseDto = userService.named(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

    /**
     * 계좌 예금주 실명 검증
     */
    @ApiOperation(value = "계좌 예금주 실명 검증",response = AccountValidationResDto.class)
    @PostMapping(value = "/account/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object namedValidate(@RequestBody @Valid AccountValidationReqDto requestDto, BindingResult bindingResult) throws Exception {
        AccountValidationResDto responseDto = new AccountValidationResDto();

        try {
            responseDto = userService.namedValidate(requestDto);

        } catch(Exception e) {
            responseDto.setResponse(CommonResponseDto.builder().code("999_999").description("기타오류").build());
            log.error("error", e);
        }
        return responseDto;
    }

}
