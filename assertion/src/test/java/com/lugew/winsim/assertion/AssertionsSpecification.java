package com.lugew.winsim.assertion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * @author LuGew
 * @since 2020/10/14
 */
@ExtendWith(MockitoExtension.class)
class AssertionsSpecification {

    @InjectMocks
    Assertions assertions = Assertions.INSTANCE;

    @Test
    void givenIsNullWhenNullThenOk() {
        assertions.isNull(null);
    }

    @Test
    void givenIsNullWhenNotNullThenError() {
        assertThatThrownBy(() -> assertions.isNull(new Object()))
                .hasMessage("must be null");
    }

    @Test
    void givenIsNullWhenNotNullThenCustomError() {
        assertThatThrownBy(() -> assertions.isNull(new Object(), "hello"))
                .hasMessage("hello");
    }

}