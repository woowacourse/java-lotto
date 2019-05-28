package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {

    @Test
    void numsInValidRange() {
        NumberGenerator generator = new RandomNumberGenerator(1, 45);
        for (int i = 0; i < 10000; i++) {
            assertThat(generator.generate())
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(45);
        }
    }

    @Test
    void numsContainsBound() {
        NumberGenerator generator = new RandomNumberGenerator(1, 45);
        boolean isMinPresent = false;
        boolean isMaxPresent = false;
        for (int i = 0; i < 10000; i++) {
            int n = generator.generate();
            if (n == 1) {
                isMinPresent = true;
            }
            if (n == 45) {
                isMaxPresent = true;
            }
        }
        assertThat(isMinPresent).isTrue();
        assertThat(isMaxPresent).isTrue();
    }
}
