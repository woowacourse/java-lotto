package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void Number_생성_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Number(-1);
            new Number(46);
        });
    }
}
