package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketsTest {
    @Test
    void create() {
        String[] lottoTickets = "1,2,3,4,5,6\n10,14,21,24,34,45\n7,9,12,19,23,38".split("\n");
        int amountOfCustom = 3;

        assertThat(new LottoTickets(lottoTickets, amountOfCustom)).isEqualTo(new LottoTickets(lottoTickets, amountOfCustom));
    }

    @Test
    void 입력받은_수만큼_수동로또를_전달받지못한경우_예외() {
        String[] lottoTickets = "1,2,3,4,5,6\n10,14,21,24,34,45\n7,9,12,19,23,38".split("\n");
        int amountOfCustoms = 4;

        assertThrows(UnmatchedLottoTicketAmountException.class, () -> new LottoTickets(lottoTickets, amountOfCustoms));
    }
}
