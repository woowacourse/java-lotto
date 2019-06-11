package lotto.domain.TicketModel;

import lotto.domain.Exceptions.LottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumbersTest {

    @Test
    void 자동생성() {
        assertNotEquals(new LottoNumbers(), new LottoNumbers());
    }

    @Test
    void 수동생성() {
        Ticket lotto = new Lotto(new LottoNumbers(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6))));
        assertTrue(lotto.contains(new LottoNumber(1)));
        assertTrue(lotto.contains(new LottoNumber(2)));
        assertTrue(lotto.contains(new LottoNumber(3)));
        assertTrue(lotto.contains(new LottoNumber(4)));
        assertTrue(lotto.contains(new LottoNumber(5)));
        assertTrue(lotto.contains(new LottoNumber(6)));
    }

    @Test
    void 갯수초과() {
        assertThrows(LottoNumberException.class, () -> {
            new LottoNumbers(Arrays.asList(new LottoNumber(1),
                    new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                    new LottoNumber(5), new LottoNumber(6), new LottoNumber(7)));
        });
    }
}