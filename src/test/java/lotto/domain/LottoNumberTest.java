package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void 숫자_범위를_넘어선_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.get(0);
        });
    }
}
