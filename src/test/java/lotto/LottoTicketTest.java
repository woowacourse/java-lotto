package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    @Test
    void compare() {
        //given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket winningLottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 10, 11, 12));
        int bonus = 13;

        assertThat(lottoTicket.compare(winningLottoTicket, bonus)).isEqualTo(Rank.FIFTH);
    }
}