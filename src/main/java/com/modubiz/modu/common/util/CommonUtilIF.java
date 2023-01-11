package com.modubiz.modu.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public interface CommonUtilIF {

    default boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    default boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    default boolean isEmpty(Object obj) {
        if(isNull(obj)) return true;
        else if(obj instanceof String) return StringUtils.isEmpty((String) obj);
        else if(obj instanceof Map) return MapUtils.isEmpty((Map) obj);
        else if(obj instanceof Collection) return CollectionUtils.isEmpty((Collection) obj);
        else if(obj instanceof Object[]) return ((Object[]) obj).length == 0;
        else return false;
    }

    default boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    default boolean isEqual(Object obj1, Object obj2) {
        if(obj1 == null && obj2 == null) return true;
        else if(obj1 == null && obj2 != null) return false;
        else if(obj1 != null && obj2 == null) return false;
        else if(obj2 instanceof String) return StringUtils.equals((String) obj1, (String) obj2);
        else if(obj2 instanceof Long) return (long) obj1 == (long) obj2;
        else if(obj2 instanceof Integer) return (int) obj1 == (int) obj2;
        else if(obj2 instanceof Float) return (float) obj1 == (float) obj2;
        else if(obj2 instanceof Double) return (double) obj1 == (double) obj2;
        else if(obj2 instanceof Boolean) return (boolean) obj1 == (boolean) obj2;
        else throw new RuntimeException("not support object type!!");
    }

    default boolean isNotEqual(Object obj1, Object obj2) {
        return !isEqual(obj1, obj2);
    }

    default Object get(Map<String, Object> map, String key) {
        return MapUtils.getObject(map, key);
    }

    default String getStr(Map<String, Object> map, String key) {
        return MapUtils.getString(map, key);
    }

    default String getStr(Map<String, Object> map, String key, String defValue) {
        return isBlank(MapUtils.getString(map, key)) ? defValue : MapUtils.getString(map, key);
    }

    default boolean getBool(Map<String, Object> map, String key) {
        return MapUtils.getBooleanValue(map, key);
    }

    default boolean getBool(Map<String, Object> map, String key, boolean defValue) {
        return MapUtils.getBooleanValue(map, key, defValue);
    }

    default int getInt(Map<String, Object> map, String key) {
        return MapUtils.getInteger(map, key);
    }

    default int getInt(Map<String, Object> map, String key, int defaultValue) {
        return MapUtils.getInteger(map, key, defaultValue);
    }

    default long getLong(Map<String, Object> map, String key) {
        return MapUtils.getLong(map, key);
    }

    default long getLong(Map<String, Object> map, String key, long defaultValue) {
        return MapUtils.getLong(map, key, defaultValue);
    }

    default Date getDate(Map<String, Object> map, String key) {
        return (Date) map.get(key);
    }

    default String getDateStr(Map<String, Object> map, String key) {
        return DateUtil.getCurDt(getDate(map, key));
    }

    default String getTimeStr(Map<String, Object> map, String key) {
        return DateUtil.getCurTm(getDate(map, key));
    }

    default String getDateTimeStr(Map<String, Object> map, String key) {
        return DateUtil.getCurTime(getDate(map, key));
    }

    default String nvl(String value1, String value2) {
        return StringUtils.defaultIfEmpty(value1, value2);
    }

    default String nvl(Object value1, String value2) {
        return StringUtils.defaultIfEmpty((String) value1, value2);
    }

    default int length(Map<String, Object> map, String key) {
        return StringUtils.length(MapUtils.getString(map, key));
    }

    default int byteLength(String str) {
        return StringUtils.isEmpty(str) ? 0 : str.getBytes().length;
    }

    default boolean contains(String str, String... arr) {
        return ArrayUtils.contains(arr, str);
    }

    default boolean isAnyEmpty(Object... arr) {
        return Arrays.stream(arr)
                .map(it -> String.valueOf(it))
                .anyMatch(it -> isEmpty(it));
    }

    default boolean isNotAnyEmpty(Object... arr) {
        return !isAnyEmpty(arr);
    }

    default boolean isAnyEmpty(String... arr) {
        return Arrays.stream(arr)
                .anyMatch(it -> isEmpty(it));
    }

    default boolean isNotAnyEmpty(String... arr) {
        return !isAnyEmpty(arr);
    }

    default boolean isBlank(String arr) {
        return StringUtils.isBlank(arr);
    }

    default boolean isNotBlank(String arr) {
        return !StringUtils.isBlank(arr);
    }

    default boolean isAllBlank(String... arr) {
        return StringUtils.isAllBlank(arr);
    }

    default boolean isNotAllBlank(String... arr) {
        return !StringUtils.isAllBlank(arr);
    }

    default boolean isAnyEqual(String value, String... arr) {
        return Arrays.stream(arr).anyMatch(it -> it.equals(value));
    }

    default Map<String, Object> makeEmptyMap() {
        return new HashMap<String, Object>();
    }

    public static Map<String, Object> getRequest(HttpServletRequest p_req)throws Exception {
        HashMap<String, Object> map       = new HashMap<String, Object>();
        Enumeration<?> _enum = p_req.getParameterNames();
        while (_enum.hasMoreElements())
        {
            String key      = (String) _enum.nextElement();
            String[] value = p_req.getParameterValues(key);
            if (value.length == 1) {
                map.put(key, p_req.getParameter(key));
            }
            else if (value.length > 1) {
                Vector<String> v = new Vector<String>();
                for (int i = 0; i < value.length; i++) {
                    v.add(v.size(), value[i]);
                }
                map.put(key, v);
            }
        }
        return map;
    }



    /**
     * CorrelationId: X-KM-Correlation-Id
     * 코나 Header 전문 추적을 위한 거래번호 생성
     * (Format : 'yyMMddHHmiSS-xxxxxxx')
     * ※ xxxxxxx : 7자리 Hex String
     * */
    default String makeCorrelationId() {
        int numChars = 7;
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numChars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        SimpleDateFormat yyMMddHHmiSS = new SimpleDateFormat("yyMMddHHmmSS");
        String currentDate = yyMMddHHmiSS.format(new Date());

        return currentDate + "-" + sb.toString().substring(0, numChars);
    }

}
