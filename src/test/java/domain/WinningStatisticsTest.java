package domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;


class WinningStatisticsTest {

    @Test
    void 당첨_통계를_산출한다() {
        List<Matcher> matchers = List.of(
                Matcher.count(
                        new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7),
                        new Lotto(List.of(1, 2, 3, 4, 5, 10))
                ),
                Matcher.count(
                        new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 10),
                        new Lotto(List.of(1, 2, 3, 4, 5, 10))
                )
        );
        assertThat(matchers)
                .extracting(Matcher::getWinningNumberCount, Matcher::isHasBonusNumber)
                .containsExactly(
                        Tuple.tuple(5, false),
                        Tuple.tuple(5, true)
                );
    }

    @Test
    void 수익률을_계산한다() {
        int purchaseAmount = 5000;
        List<Matcher> matchers = List.of(
                Matcher.count(
                        new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 45),
                        new Lotto(List.of(1, 2, 3, 10, 11, 12))
                )
        );
        List<WinningCounter> winningCounters = WinningCounter.count(matchers);
        Yield yield = Yield.calculate(purchaseAmount, winningCounters);

        assertThat(yield.getYield()).isEqualTo(1);
    }
}