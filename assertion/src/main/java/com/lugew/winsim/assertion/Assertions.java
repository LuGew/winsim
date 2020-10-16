package com.lugew.winsim.assertion;

import com.lugew.winsim.assertion.exception.AssertionException;

import java.util.Collection;

/**
 * @author LuGew
 * @since 2020/10/14
 */
public enum Assertions {
    INSTANCE;
    private final static String EMPTY = "";
    
    public void isNull(Object value) {
        isNull(value, "must be null");
    }

    public void isNull(Object value, String message) {
        if (null != value) {
            throw new AssertionException(message);
        }
    }

    public void isNull(String message, Object... objects) {
        if (null != objects) {
            for (Object object : objects) {
                isNull(object, message);
            }
        }
    }

    public void isNull(Object... objects) {
        isNull("must be null", objects);
    }

    public void isEmpty(String value) {
        isEmpty(value, "must be empty");
    }

    public void isEmpty(String value, String message) {
        if (null != value) {
            throw new AssertionException(message);
        }
    }

    public void isNotEmpty(String value) {
        isNotEmpty(value, "must not be empty");
    }

    public void isNotEmpty(String value, String message) {
        if (null == value || EMPTY.equals(value)) {
            throw new AssertionException(message);
        }
    }

    public void isEmpty(Collection<?> value) {
        isEmpty(value, "must be empty");
    }

    public void isEmpty(Collection<?> value, String message) {
        if (null != value) {
            throw new AssertionException(message);
        }
    }

    public void isNotEmpty(Collection<?> value) {
        isNotEmpty(value, "must be empty");
    }

    public void isNotEmpty(Collection<?> value, String message) {
        if (null == value || value.size() == 0) {
            throw new AssertionException(message);
        }
    }

    public void isTrue(Boolean value) {
        isTrue(value, "must be true");
    }

    public void isTrue(Boolean value, String message) {
        if (null == value || !value) {
            throw new AssertionException(message);
        }
    }

    public void isFalse(Boolean value) {
        isFalse(value, "must be false");
    }

    public void isFalse(Boolean value, String message) {
        if (null == value || value) {
            throw new AssertionException(message);
        }
    }

    public <T> void isEqual(T source, T target) {
        if (null == source && null != target) {
        } else if (!source.equals(target)) {
            throw new AssertionException("must equal");
        }
    }


}
