package lotto.domain;

import lotto.exception.IllegalNumberBoundException;
import lotto.exception.UnmatchedLottoTicketAmountException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketFactoryTest {
    @Test
    void create() {
        String numbers = "1,2,3,4,5,6";
        assertThat(LottoTicketFactory.create(numbers)).isEqualTo(LottoTicketFactory.create(numbers));
    }

    @Test
    void LottoTicket객체를_반환하는지_확인() {
        String numbers = "1,2,3,4,5,6";
        assertThat(LottoTicketFactory.create(numbers)).isInstanceOf(LottoTicket.class);
    }

    @Test
    void 문자가_입력됐을경우_예외처리() {
        String numbers = "1,2,3,4,5,e";
        assertThrows(ArithmeticException.class, () -> LottoTicketFactory.create(numbers));
    }

    @Test
    void 범위밖의_로또번호_예외처리() {
        String numbers = "1,2,3,4,5,0";
        assertThrows(IllegalNumberBoundException.class, () -> LottoTicketFactory.create(numbers));
    }

    @Test
    void 중복된_로또번호_예외() {
        String numbers = "1,2,3,4,5,5";
        assertThrows(UnmatchedLottoTicketAmountException.class, () -> LottoTicketFactory.create(numbers));
    }
}
