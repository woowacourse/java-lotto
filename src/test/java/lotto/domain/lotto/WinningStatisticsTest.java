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
            LottoNumbers.valueOf("1,2,3,11,12,13"),
            LottoNumbers.valueOf("7,8,9,10,11,12")
        ));

        winningStatistics = lottoTicket.getWinningStatistics(winningNumbers);
    }

    @Test
    @DisplayName("당첨 통계 확인")
    void newWinningStatistics() {
        Map<Rank, Long> expected = new HashMap<>();
        Arrays.stream(Rank.values()).forEach(rank -> expected.put(rank, 1L));

        assertThat(winningStatistics.getRanks().unwrap()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률 확인")
    void getYield() {
        int totalWinnings = Arrays.stream(Rank.values())
            .mapToInt(Rank::getWinnings)
            .sum();

        assertThat(winningStatistics.getYield().unwrap())
            .isEqualTo((double) totalWinnings / 6000, withPrecision(2d));
    }
}