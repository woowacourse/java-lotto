package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void create() {
        assertThat(LottoNumber.from(15)).isEqualTo(LottoNumber.from(15));
    }

    @Test
    void 범위를_벗어난_숫자() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.from(49));
    }
}
