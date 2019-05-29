package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @Test
    void 범위밖의수_입력1() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.valueOf(46);
        });
    }

    @Test
    void 범위밖의수_입력2() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.valueOf(0);
        });
    }

    @Test
    void 같은번호_확인() {
        LottoNumber first = LottoNumber.valueOf(1);
        LottoNumber second = LottoNumber.valueOf(1);

        assertThat(first).isEqualTo(second);
    }

    @Test
    void 다른번호_확인() {
        LottoNumber first = LottoNumber.valueOf(1);
        LottoNumber second = LottoNumber.valueOf(2);

        assertThat(first).isNotEqualTo(second);
    }
}