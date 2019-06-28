package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidNumberRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    public void 로또_번호_범위_위_초과_예외() {
        assertThrows(InvalidNumberRangeException.class, () -> {
            LottoNumber.of(46);
        });
    }

    @Test
    public void 로또_번호_범위_아래_초과_예외() {
        assertThrows(InvalidNumberRangeException.class, () -> {
            LottoNumber.of(0);
        });
    }

    @Test
    public void 로또_숫자_생성_일치() {
        assertThat(LottoNumber.of(5).toString()).isEqualTo("5");
    }
}