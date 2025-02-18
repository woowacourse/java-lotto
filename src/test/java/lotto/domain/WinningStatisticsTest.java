package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatisticsTest {
    private WinningStatistics winningStatistics;

    @BeforeEach
    void setUp() {
        List<Lotto> lottos = List.of(new Lotto(Set.of(1, 2, 3, 7, 8, 9)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(45);
        winningStatistics = new WinningStatistics(lottos, winningNumbers, bonusNumber);
    }

    @DisplayName("구입한 로또와 당첨 번호, 보너스 번호를 비교하여 당첨 통계를 반환한다")
    @CsvSource(value = {"FIRST:0", "SECOND:0", "THIRD:0", "FOURTH:0", "FIFTH:1", "NONE:0"}, delimiterString = ":")
    @ParameterizedTest
    void 구입한_로또와_당첨번호_보너스번호를_비교하여_당첨통계를_반환한다(Rank rank, int expected) {
        assertThat(winningStatistics.getRankCount(rank)).isEqualTo(expected);
    }

    @DisplayName("소수점 셋째자리에서 버림한 수익률을 반환한다")
    @Test
    void 소수점_셋째자리에서_버림한_수익률을_반환한다() {
        assertThat(winningStatistics.calculateReturnRate(1)).isEqualTo(5.00);
    }

    @DisplayName("당첨 등수의 개수를 반환한다")
    @Test
    void 당첨_등수의_개수를_반환한다() {
        assertThat(winningStatistics.getRankCount(Rank.FIFTH)).isEqualTo(1);
    }
}
