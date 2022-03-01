package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatsTest {

    @Test
    @DisplayName("당첨 금액을 맞추면 맞춘 횟수가 +1 만큼 된다")
    void getCorrectAnswerNumbers() {
        // given
        LottoTicket lottoTicket = new LottoTicket("1, 2, 3, 4, 5, 6");
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket("1, 2, 3, 20, 21, 22"),
                new LottoNumber("30"));
        WinningStats winningStats = new WinningStats(List.of(lottoTicket), winningNumbers);

        // when
        int correctAnswerNumbers = winningStats.getCorrectAnswerNumbers(LottoRank.FIFTH);

        // then
        assertThat(correctAnswerNumbers).isEqualTo(1);
    }


    @Test
    @DisplayName("당첨 금액의 총합을 반환한다")
    void getTotalPrize() {
        // given
        LottoTicket lottoTicket = new LottoTicket("1, 2, 3, 4, 5, 6");
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket("1, 2, 3, 4, 5, 20"),
                new LottoNumber("6"));
        WinningStats winningStats = new WinningStats(List.of(lottoTicket), winningNumbers);

        // when
        long totalPrize = winningStats.getTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(LottoRank.SECOND.prizeMoney());
    }

    @Test
    @DisplayName("수익률을 반환한다")
    void getEarningsRate() {
        // given
        LottoTicket lottoTicket = new LottoTicket("1, 2, 3, 4, 5, 6");
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket("1, 2, 3, 10, 11, 12"),
                new LottoNumber("13"));
        WinningStats winningStats = new WinningStats(List.of(lottoTicket), winningNumbers);
        PurchaseAmount money = new PurchaseAmount("10000");

        // when
        double earningsRate = winningStats.getEarningsRate(money).doubleValue();

        // then
        assertThat(earningsRate).isEqualTo(0.5);
    }
}
