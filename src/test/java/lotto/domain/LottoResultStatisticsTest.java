package lotto.domain;

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
        LottoWinnerTicket lottoWinnerTicket = new LottoWinnerTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );

        LottoWinnerBonusNumber lottoWinnerBonusNumber = new LottoWinnerBonusNumber(7);

        lottoWinner = new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);

        LottoTicket lottoTicket1 = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );
        LottoTicket lottoTicket2 = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(7)
                )
        );
        LottoTicket lottoTicket3 = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(9),
                        new LottoNumber(10),
                        new LottoNumber(43),
                        new LottoNumber(44)
                )
        );
        lottoTickets = new LottoTickets(Arrays.asList(lottoTicket1, lottoTicket2, lottoTicket3));
    }

    @Test
    @DisplayName("결과를 생성한다.")
    public void createLottoResult() {
        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.getResultStatistics(lottoTickets, lottoWinner);

        assertThat(lottoResultStatistics).isInstanceOf(LottoResultStatistics.class);
    }

    @Test
    @DisplayName("결과를 추출한다.")
    public void extractResult() {
        Map<LottoRank, Integer> result =
                LottoResultStatistics.getResultStatistics(lottoTickets, lottoWinner).getLottoResult();

        assertThat(result.get(LottoRank.FIRST_PLACE)).isEqualTo(1);
        assertThat(result.get(LottoRank.SECOND_PLACE)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD_PLACE)).isEqualTo(0);
        assertThat(result.get(LottoRank.FOURTH_PLACE)).isEqualTo(0);
        assertThat(result.get(LottoRank.FIFTH_PLACE)).isEqualTo(0);
        assertThat(result.get(LottoRank.SIXTH_PLACE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산한다.")
    public void calculateEarningTest() {
        Money money = new Money(3000);

        int earningPercentage =
                LottoResultStatistics.getResultStatistics(lottoTickets, lottoWinner).calculateEarning(money);

        assertThat(earningPercentage).isEqualTo(67666566);
    }
}
