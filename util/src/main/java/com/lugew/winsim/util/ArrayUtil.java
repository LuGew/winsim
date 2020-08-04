package com.lugew.winsim.util;

import lombok.experimental.UtilityClass;

/**
 * @author LuGew
 * @since 2020/7/31
 */
@UtilityClass
public class ArrayUtil {

    public boolean isEmpty(Object[] objects) {
        return null == objects || objects.length == 0;
    }

    public boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }


}
