package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberTest {
    @Test
    void getNumber() {
        assertThat(Number.of(4)).isEqualTo(Number.of(4));
    }
}
