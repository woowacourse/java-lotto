package lotto.domain.lottoresult;

import lotto.domain.lotto.LottoStrategy.ManualLottoStrategy;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    private LottoTicketGroup lottoTickets;
    private LottoResult lottoResult;

    @BeforeEach
    void setup() {
        lottoTickets = new LottoTicketGroup(Arrays.asList(
                LottoTicket.create(new ManualLottoStrategy("1, 2, 3, 8, 9, 10")),
                LottoTicket.create(new ManualLottoStrategy("7, 8, 9, 1, 2, 3"))
        ));
        lottoResult = new LottoResult(
                WinningLotto.create("1, 2, 3, 4, 5, 6", "15"),
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
        assertThat(lottoResult.getEarningRate())
                .isEqualTo( new BigDecimal(10000).divide(new BigDecimal(20), 4, RoundingMode.HALF_UP));
    }
}
