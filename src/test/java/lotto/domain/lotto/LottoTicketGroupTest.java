package lotto.domain.lotto;

import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketGroupTest {
    private LottoTicketGroup lottoTickets;

    @BeforeEach
    void setUp() {
        lottoTickets = new LottoTicketGroup(
                Arrays.asList(LottoTicket.create(()->Arrays.asList(1,2,3,4,5,6)))
        );
    }

    @Test
    void priceTest() {
        assertThat(lottoTickets.price()).isEqualTo(1000);
    }

    @Test
    void matchTest() {
        LottoResult lottoResult = new LottoResult(Arrays.asList(LottoRank.FIRST));
        WinningLotto winningLotto = WinningLotto.create("1, 2, 3, 4, 5, 6", "7");

        assertThat(lottoResult).isEqualTo(lottoTickets.match(winningLotto));
    }
}