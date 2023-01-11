package com.modubiz.modu.common.dto;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;

public class CamelKeyMap extends ListOrderedMap {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7700790403928325865L;


    /**
     * key 에 대하여 소문자로 변환하여 super.put (ListOrderedMap) 을 호출한다.
     *
     * @param key   - '_' 가 포함된 변수명
     * @param value - 명시된 key 에 대한 값 (변경 없음)
     * @return previous value associated with specified key, or null if there was no mapping for key
     */
    public Object put(Object key, Object value) {

        if (((String) key).split("_").length == 1 && !((String) key).toLowerCase().equals((String) key) && !((String) key).toUpperCase().equals((String) key)) {
        } else {
            StringBuffer buffer = new StringBuffer();
            for (String token : ((String) key).toLowerCase().split("_")) {
                buffer.append(StringUtils.capitalize(token));
            }
            key = StringUtils.uncapitalize(buffer.toString());
        }
        return super.put(key, value);
    }

    /**
     * key 에 대하여 소문자로 변환하여 super.put (ListOrderedMap) 을 호출한다.
     *
     * @param key   - '_' 가 포함된 변수명
     * @param value - 명시된 key 에 대한 값 (변경 없음)
     * @return previous value associated with specified key, or null if there was no mapping for key
     */
    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }
}

