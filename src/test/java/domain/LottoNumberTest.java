package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    void 로또_숫자_0이하() {
        assertThatThrownBy(() -> new LottoNumber(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_46() {
        assertThatThrownBy(() -> new LottoNumber(46))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_1() {
        assertThatCode(() -> new LottoNumber(1))
            .doesNotThrowAnyException();
    }

    @Test
    void 로또_숫자_23() {
        assertThatCode(() -> new LottoNumber(23))
            .doesNotThrowAnyException();
    }

    @Test
    void 로또_숫자_45() {
        assertThatCode(() -> new LottoNumber(45))
            .doesNotThrowAnyException();
    }
}
