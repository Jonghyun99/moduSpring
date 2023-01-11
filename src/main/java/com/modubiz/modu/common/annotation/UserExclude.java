package com.modubiz.modu.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DTO to JSON변환(API요청) 시 불필요한 필드 제거
 *
 * ex)
 * ----기존 DTO----
 * private String id;
 * private String pw;
 *
 * {
 *     "id":"001"
 *     "pw":"password"
 * }
 *
 *
 * ----UserExclude 사용 시----
 * private String id;
 *
 * @UserExclude
 * private String pw;
 *
 * {
 *     "id":"001"
 * }
 *
 *
 *
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UserExclude {
}
