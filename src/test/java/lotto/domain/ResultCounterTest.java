package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.game.ResultCounter;

import static org.assertj.core.api.Assertions.assertThat;

class ResultCounterTest {
    @Test
    void multiply_test_0x3() {
        assertThat(new ResultCounter().multiply(3)).isEqualTo(0);
    }

    @Test
    void multiply_test_1x3() {
        assertThat(new ResultCounter().increase().multiply(3)).isEqualTo(3);
    }

    @Test
    void multiply_test_2x3() {
        assertThat(new ResultCounter().increase().increase().multiply(3)).isEqualTo(6);
    }
}