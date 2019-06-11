package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.game.ResultCounter;

import static org.assertj.core.api.Assertions.assertThat;

class ResultCounterTest {
    @Test
    void multiply_test_0x3() {
        assertThat(new ResultCounter().multiply(3)).isEqualTo(0);
    }
}