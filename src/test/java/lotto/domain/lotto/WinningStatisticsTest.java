package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    WinningStatistics winningStatistics;

    @BeforeEach
    void setUp() {
        WinningNumbers winningNumbers = WinningNumbers.valueOf("1, 2, 3, 4, 5, 6", "7");
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,7"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,11,12"),
            LottoNumbers.valueOf("1,2,3,11,12,13")
        ));

        winningStatistics = new WinningStatistics(lottoTicket, winningNumbers);
    }

    @Test
    @DisplayName("당첨 통계 확인")
    void newWinningStatistics() {
        Map<Rank, Long> expected = new HashMap<>();
        Rank.getAllPossibleRanks().stream().forEach(rank -> expected.put(rank, 1L));

        assertThat(winningStatistics.unwrap()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률 확인")
    void getYield() {
        int totalWinnings = Rank.getAllPossibleRanks().stream()
            .mapToInt(Rank::getWinnings)
            .sum();

        assertThat(winningStatistics.getYield())
            .isEqualTo((double) totalWinnings / 5000, withPrecision(2d));
    }
}