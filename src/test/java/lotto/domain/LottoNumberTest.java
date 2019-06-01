package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 객체를_재사용하는지_확인() {
        assertThat(LottoNumber.valueOf(1)).isEqualTo(LottoNumber.valueOf(1));
    }

    @Test
    void 로또_번호가_1미만인_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(0));
    }

    @Test
    void 로또_번호가_45초과인_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(46));
    }
}
