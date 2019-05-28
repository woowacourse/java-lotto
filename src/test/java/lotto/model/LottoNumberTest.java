package lotto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 올바른_로또_번호_범위일_때_확인() {
        assertDoesNotThrow(() -> new LottoNumber(1));
        assertDoesNotThrow(() -> new LottoNumber(45));
    }

    @Test
    void 로또_번호_범위를_벗어날_때_예외_발생() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
    }
}
