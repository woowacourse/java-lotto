package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoResultTest {
    LottoTicketGroup lottoTickets;
    LottoResult lottoResult;

    @BeforeEach
    void setup() {
        lottoTickets = new LottoTicketGroup(Arrays.asList(
                LottoTicket.create(Arrays.asList(1, 2, 3, 8, 9, 10)),
                LottoTicket.create(Arrays.asList(7, 8, 9, 1, 2, 3))
                ));
        lottoResult = new LottoResult(
                WinningLotto.create(LottoTicket.create("1, 2, 3, 4, 5, 6"), "15"),
                lottoTickets
        );
    }

    @Test
    void 통계정보_확인1() {
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(3, false))).isEqualTo(2);
    }

    @Test
    void 통계정보_확인2() {
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(2, false))).isEqualTo(0);
    }

    @Test
    void 수익률_확인() {
        assertEquals(10000 / 20, lottoResult.earningRate(), 0.1);
    }
}
