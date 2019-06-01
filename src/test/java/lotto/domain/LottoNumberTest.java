package lotto.domain;

import lotto.exception.NumberValidException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 사십오_보다_큰_예외생성() {
        assertThrows(NumberValidException.class, () -> {
            LottoNumber.of(46);
        });
    }

    @Test
    void 일_보다_작은_예외생성() {
        assertThrows(NumberValidException.class, () -> {
            LottoNumber.of(0);
        });
    }

    @Test
    void 로또_번호_생성() {
        assertThat(LottoNumber.of(19)).isEqualTo(new LottoNumber(19));
    }
}
