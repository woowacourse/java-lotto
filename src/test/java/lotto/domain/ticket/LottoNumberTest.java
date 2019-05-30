package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidNumberRangeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    public void 로또_번호_범위_위_초과_예외(){
        assertThrows(InvalidNumberRangeException.class, () -> {
            new LottoNumber(46);
        });
    }

    @Test
    public void 로또_번호_범위_아래_초과_예외(){
        assertThrows(InvalidNumberRangeException.class, () -> {
            new LottoNumber(0);
        });
    }
}