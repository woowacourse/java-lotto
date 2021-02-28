package lotto.domain;

import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultStatisticsTest {
    private LottoTickets lottoTickets;
    private LottoWinner lottoWinner;

    @BeforeEach
    public void setUp() {
        LottoTicket lottoWinnerTicket = LottoTicket.createManualLottoTicket("1,2,3,4,5,6");
        LottoBonusNumber lottoBonusNumber = LottoBonusNumber.of("7", lottoWinnerTicket);
        lottoWinner = new LottoWinner(lottoWinnerTicket, lottoBonusNumber);

        LottoTicket lottoTicket1 = LottoTicket.createManualLottoTicket("1,2,3,4,5,6");
        LottoTicket lottoTicket2 = LottoTicket.createManualLottoTicket("1,2,3,4,5,7");
        LottoTicket lottoTicket3 = LottoTicket.createManualLottoTicket("1,2,3,4,5,8");
        LottoTicket lottoTicket4 = LottoTicket.createManualLottoTicket("1,2,9,43,44,45");
        lottoTickets = new LottoTickets(Arrays.asList(lottoTicket1, lottoTicket2, lottoTicket3, lottoTicket4));
    }

    @Test
    @DisplayName("결과를 생성한다.")
    public void createLottoResult() {
        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.calculateResultStatistics(lottoTickets, lottoWinner);

        assertThat(lottoResultStatistics).isInstanceOf(LottoResultStatistics.class);
    }

    @Test
    @DisplayName("결과를 추출한다.")
    public void extractResult() {
        Map<LottoRank, Integer> result =
                LottoResultStatistics.calculateResultStatistics(lottoTickets, lottoWinner).getLottoResult();

        assertThat(result.get(LottoRank.FIRST_PLACE)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND_PLACE)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD_PLACE)).isEqualTo(1);
        assertThat(result.get(LottoRank.FOURTH_PLACE)).isEqualTo(0);
        assertThat(result.get(LottoRank.FIFTH_PLACE)).isEqualTo(0);
        assertThat(result.get(LottoRank.SIXTH_PLACE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산한다.")
    public void calculateEarningTest() {
        Money money = new Money("3000");

        long earningPercentage =
                LottoResultStatistics.calculateResultStatistics(lottoTickets, lottoWinner).calculateEarning(money);

        assertThat(earningPercentage).isEqualTo(67716566);
    }
}
