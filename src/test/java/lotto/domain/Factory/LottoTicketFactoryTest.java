package lotto.domain.Factory;

import lotto.domain.LottoTicket;
import lotto.exception.DuplicatedInputException;
import lotto.exception.UnexpectedInputRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketFactoryTest {
    @Test
    void 수동로또_입력값_넣었을때_create() {
        String numbers = "1,2,3,4,5,6";
        assertThat(new LottoTicketFactory().create(numbers)).isEqualTo(new LottoTicketFactory().create(numbers));
    }

    @Test
    void LottoTicket객체를_반환하는지_확인() {
        String numbers = "1,2,3,4,5,6";
        assertThat(new LottoTicketFactory().create(numbers)).isInstanceOf(LottoTicket.class);
    }

    @Test
    void 문자가_입력됐을경우_예외처리() {
        String numbers = "1,2,3,4,5,e";
        assertThrows(ArithmeticException.class, () -> new LottoTicketFactory().create(numbers));
    }

    @Test
    void 범위밖의_로또번호_예외처리() {
        String numbers = "1,2,3,4,5,0";
        assertThrows(UnexpectedInputRangeException.class, () -> new LottoTicketFactory().create(numbers));
    }

    @Test
    void 중복된_로또번호_예외() {
        String numbers = "1,2,3,4,5,5";
        assertThrows(DuplicatedInputException.class, () -> new LottoTicketFactory().create(numbers));
    }
}