package com.lugew.winsim.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LuGew
 * @since 2020/5/18
 */
@UtilityClass
@Slf4j
public class StringUtil {
    public final String EMPTY = "";

    public boolean isEmpty(String s) {
        return null == s || EMPTY.equals(s);
    }

    public boolean isNotEmpty(Object object) {
        return object instanceof String && !((String) object).isEmpty();
    }

    public boolean isEmpty(Object object) {
        return !isNotEmpty(object);
    }

    public boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public boolean allEqualsNotNull(String... strings) {
        String s1 = strings[0];
        if (null == s1) {
            throw new RuntimeException("element is null");
        }
        for (int i = 1, length = strings.length; i < length; i++) {
            if (!s1.equals(strings[i])) {
                return false;
            }
        }
        return true;
    }
}
