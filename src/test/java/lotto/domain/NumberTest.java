package lotto.domain;

import lotto.exception.IllegalNumberBoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void 범위밖의_로또_번호에_대한_예외() {
        assertThrows(IllegalNumberBoundException.class, () -> { Number.getNumber(0); });
    }

    @Test
    void 같은_숫자일때_메모리주소_테스트() {
        assertThat(Number.getNumber(1) == Number.getNumber(1)).isTrue();
    }

    @Test
    void 다른_숫자일때_메모리주소_테스트() {
        assertThat(Number.getNumber(1) == Number.getNumber(2)).isFalse();
    }
}
