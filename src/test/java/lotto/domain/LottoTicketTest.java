package lotto.domain;

import lotto.exception.IllegalNumberBoundException;
import lotto.exception.UnmatchedLottoTicketAmountException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {
    @Test
    void create() {
        String numbers = "1,2,3,4,5,6";
        assertThat(new LottoTicket(numbers)).isEqualTo(new LottoTicket(numbers));
    }

    @Test
    void 문자가_입력됐을경우_예외처리() {
        String numbers = "1,2,3,4,5,e";
        assertThrows(ArithmeticException.class, () -> new LottoTicket(numbers));
    }

    @Test
    void 범위밖의_로또번호_예외처리() {
        String numbers = "1,2,3,4,5,0";
        assertThrows(IllegalNumberBoundException.class, () -> new LottoTicket(numbers));
    }

    @Test
    void 중복된_로또번호_예외() {
        String numbers = "1,2,3,4,5,5";
        assertThrows(UnmatchedLottoTicketAmountException.class, () -> new LottoTicket(numbers));
    }
}
