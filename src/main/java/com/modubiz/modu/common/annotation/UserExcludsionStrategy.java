package com.modubiz.modu.common.annotation;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * DTO to JSON변환(API요청) 시 불필요한 필드 제거
 * */
public class UserExcludsionStrategy implements ExclusionStrategy {


    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(UserExclude.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
