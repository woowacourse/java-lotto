package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {
    @Test
    @DisplayName("당첨 등수가 나오면 해당 등수의 개수를 +1 한다")
    void putLottoRank() {
        // given
        Lotto lotto = Lotto.from(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList()));
        WinningLotto winningLotto = WinningLotto.of(List.of(1,2,3,8,9,10), 7);
        LottoPurchasingMoney money = LottoPurchasingMoney.valueOf(10000);

        // when
        WinningStatistics winningStatistics = WinningStatistics.of(List.of(lotto), winningLotto, money);
        int result = winningStatistics.getWinningCounts().get(LottoRank.FIFTH);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 반환한다")
    void getEarningsRate() {
        // given
        Lotto lotto = Lotto.from(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList()));
        WinningLotto winningLotto = WinningLotto.of(List.of(1,2,3,8,9,10), 7);
        LottoPurchasingMoney money = LottoPurchasingMoney.valueOf(10000);

        // when
        WinningStatistics winningStatistics = WinningStatistics.of(List.of(lotto), winningLotto, money);
        double result = winningStatistics.getEarningsRate();

        // then
        assertThat(result).isEqualTo(0.5);
    }
}
