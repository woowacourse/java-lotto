package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidDuplicatedNumberException;
import lotto.domain.ticket.exception.InvalidNumberCountException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    @Test
    public void 로또_번호_갯수_초과_아래_예외() {
        assertThrows(InvalidNumberCountException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1)));
        });
    }

    @Test
    public void 로또_번호_갯수_초과_위_예외() {
        assertThrows(InvalidNumberCountException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6), LottoNumber.of(7)));
        });
    }


    @Test
    public void 로또_번호_중복_예외() {
        assertThrows(InvalidDuplicatedNumberException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(5)));
        });
    }

}