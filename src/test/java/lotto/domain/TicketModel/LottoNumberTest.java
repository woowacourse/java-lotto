package lotto.domain.TicketModel;

import lotto.domain.Exceptions.LottoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void 최소숫자() {
        assertThrows(LottoNumberException.class, () -> {
            new LottoNumber(0);
        });
    }

    @Test
    void 최대숫자() {
        assertThrows(LottoNumberException.class, () -> new LottoNumber(46));
    }
}