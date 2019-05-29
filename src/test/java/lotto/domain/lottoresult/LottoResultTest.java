package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoTicket;
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
                WinningLotto.create(Arrays.asList(1, 2, 3, 4, 5, 6)),
                lottoTickets
        );
    }

    @Test
    void 통계정보_확인1() {
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(3))).isEqualTo(2);
    }

    @Test
    void 통계정보_확인2() {
        assertThat(lottoResult.countOfRank(LottoRank.rankOf(2))).isEqualTo(0);
    }

    @Test
    void 수익률_확인() {
        assertEquals(10000 / 20, lottoResult.earningRate(), 0.1);
    }
}
