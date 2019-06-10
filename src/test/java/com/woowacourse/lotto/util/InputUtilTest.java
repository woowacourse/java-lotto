package com.woowacourse.lotto.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputUtilTest {

    @Test
    void separateNumbersByComma() {
        String str = "  1,   5   , 2, 10, 2,100";
        assertThat(InputUtil.splitByComma(str)).containsExactly(1, 5, 2, 10, 2, 100);
    }
}
