package com.modubiz.modu.common.util;

import com.modubiz.modu.common.dto.ValidateCheckResDto;
import com.modubiz.modu.kona.dto.common.CommonResponseDto;
import com.modubiz.modu.kona.dto.common.CommonResponseWrapper;
import com.modubiz.modu.kona.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static com.modubiz.modu.common.constants.ResultMsgCode.OK;

@Component
@Aspect
@Slf4j
@SuppressWarnings("unchecked")
public class CommonAdvice implements CommonUtilIF {

    @Autowired
    ValidateCheck validateCheck;

    @Autowired
    CommonService commonService;

    @Pointcut("execution(* com.modubiz.modu..controller.*.*(..))")
    public void controllerPointCut() {}

    @Pointcut("@annotation(com.modubiz.modu.common.annotation.AopLogging)")
    public void detailPointcut() {
    }

    // 컨트롤러 공통 처리 (요청 로깅, 유효성검증, 응답 로깅)
    @Around("controllerPointCut()")
    public Object controllerLogging(ProceedingJoinPoint pjp) throws Throwable {
        MDC.put("uniqKey", commonService.generateUUID(20));

        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        log.info("================{}.{} Start================", className, methodName);

        // 요청 객체 할당
        Object[] args = pjp.getArgs();

        // 요청 로깅
        if (args.length<=0){
            log.info("================={} Request=N/A", methodName);
        } else {
            log.info("================={} Request={}", methodName, args[0].toString());
        }

        // 유효성 검증
        CommonResponseWrapper responseDto = new CommonResponseWrapper(); // 응답 DTO 선언
        for(Object arg : args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                ValidateCheckResDto validateCheckResDto = validateCheck.checkValidate(bindingResult);
                if(isNotEqual(validateCheckResDto.getValidResultCd(), OK.getRstCd())) {
                    log.info("[{}_{}_validCheck] = {}", className, methodName, validateCheckResDto);
                    responseDto.setResponse(CommonResponseDto.builder().code(validateCheckResDto.getValidResultCd()).description(validateCheckResDto.getValidResultMsg()).build());
                    return responseDto;
                }
            }
        }

        // 응답 로깅
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed(); // proceed는 Exception 보다 상위 Throwable을 처리해야 한다.
        long endTime = System.currentTimeMillis();


        if (isNotNull(result)){
            log.info("================={} Response={}", methodName, result.toString());
        } else{
            log.info("================={} Response=N/A", methodName);
        }
        log.info("=================elapsed time : {}ms=================",(endTime - startTime));
        log.info("================={}.{} End=================", className, methodName);
        return result;
    }

    @Around("detailPointcut()")
    public Object detailLogging(ProceedingJoinPoint pjp) throws Throwable {
        log.info("============={} Start================", pjp.getTarget().getClass().getSimpleName());
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        Object[] args = pjp.getArgs();
        if (args.length<=0){
            log.info("[{}_{}_no parameter]",className,methodName);
        } else {
            log.info("[{}_{}_Param]: {}", className, methodName, args[0].toString());
        }

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed(); // proceed는 Exception 보다 상위 Throwable을 처리해야 한다.
        long endTime = System.currentTimeMillis();

        if (isNotNull(result)){
            log.info("[{}_{}_Response] - elapsed time {}ms: \n {}", className, methodName, (endTime - startTime), result.toString());
        } else{
            log.info("[{}_{}_Response] - elapsed time {}ms", className, methodName, (endTime - startTime));
        }
        return result;
    }
}
