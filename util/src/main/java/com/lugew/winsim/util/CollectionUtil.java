package com.lugew.winsim.util;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * @author LuGew
 * @since 2020/7/31
 */
@UtilityClass
public class CollectionUtil {
    public boolean isNotEmpty(Object object) {
        return object instanceof Collection && !((Collection<?>) object).isEmpty();
    }

    public boolean isEmpty(Object object) {
        return !isNotEmpty(object);
    }
}
