package com.modubiz.modu.common.util;

import com.modubiz.modu.common.constants.ResultMsgCode;
import com.modubiz.modu.common.dto.ValidateCheckResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;


// DTO 유효성 검증 처리 로직
@Slf4j
@Service
public class ValidateCheck {

    // 유효성 위반 인자 처리 로직
    public ValidateCheckResDto checkValidate(BindingResult bindingResult){
        ValidateCheckResDto validateCheckResDto = new ValidateCheckResDto();
        try{
            if(bindingResult.hasErrors()){
                List<FieldError> list = bindingResult.getFieldErrors();
                for(FieldError e : list){
                    String errMsg = "";
                    String errCode = "";
                    switch (e.getCode()){
                        case "NotBlank":
                            errMsg = "필수값 누락["+e.getField()+"]";
                            errCode = e.getDefaultMessage();
                            break;
                        case "Size":
                            errMsg = "길이 오류["+e.getField()+"]";
                            errCode = e.getDefaultMessage();
                            break;
                        case "Email":
                        case "DateTimeFormat":
                        case "NumberFormat":
                            errMsg = "데이터 형식 오류["+e.getField()+"]";
                            errCode = "9999";
                            break;
                        default:
                            errMsg ="필드 오류["+e.getField()+"]";
                            errCode = "9999";
                            break;
                    };
                    validateCheckResDto.setValidResultCd(errCode);
                    validateCheckResDto.setValidResultMsg(errMsg);
                    log.info("유효성 체크 = {}", validateCheckResDto.toString());
                }
            } else {
                validateCheckResDto.setValidResultCd(ResultMsgCode.OK.getRstCd());
                validateCheckResDto.setValidResultMsg(ResultMsgCode.OK.getRstMsg());
            }
        } catch (Exception e){
            log.error("validateCheckErr!!",e);
        }
        return validateCheckResDto;
    }



}
