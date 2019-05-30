package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void Number_생성_테스트_MIN() {
        assertThrows(IllegalArgumentException.class, () -> {
            Number.valueOf(-1);
        });
    }

    @Test
    void Number_생성_테스트_MAX() {
        assertThrows(IllegalArgumentException.class, () -> {
            Number.valueOf(46);
        });
    }
}
