package com.lugew.winsim.util;

import lombok.experimental.UtilityClass;

/**
 * @author LuGew
 * @since 2020/7/31
 */
@UtilityClass
public class ObjectUtil {
    public boolean isNull(Object object) {
        return null == object;
    }

    public boolean isNotNull(Object object) {
        return !isNull(object);
    }
}
