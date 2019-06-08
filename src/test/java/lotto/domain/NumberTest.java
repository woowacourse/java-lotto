package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void 숫자가_45_이상일때() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Number(46);
        });
    }

    @Test
    void 숫자가_1_이하일때() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Number(0);
        });
    }
}
