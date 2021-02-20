package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    private WinningResult winningResult;

    @BeforeEach
    void setUp() {
        winningResult = new WinningResult(createValidWinningNumbers(), createValidLottoTickets(),
                createValidPrice());
    }

    @DisplayName("각 등수별 일치 로또 티켓 개수 확인")
    @Test
    void countNumberOfRank_success() {
        assertAll(
                () -> assertThat(winningResult.countNumberOfRank(Ranking.FIRST)).isEqualTo(1),
                () -> assertThat(winningResult.countNumberOfRank(Ranking.SECOND)).isEqualTo(1),
                () -> assertThat(winningResult.countNumberOfRank(Ranking.THIRD)).isEqualTo(0),
                () -> assertThat(winningResult.countNumberOfRank(Ranking.FIFTH)).isEqualTo(1)
        );
    }

    @DisplayName("총 수익률 계산 성공")
    @Test
    void getProfitRate_success() {
        assertThat(winningResult.getProfitRate()).isEqualTo(2_030_005_000.0 / 3000.0);
    }

    private Price createValidPrice() {
        return Price.valueOf("3000");
    }

    private List<LottoTicket> createValidLottoTickets() {
        return Arrays.asList(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.valueOf(Arrays.asList(4, 5, 6, 7, 8, 9)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 9))
        );
    }

    private WinningNumbers createValidWinningNumbers() {
        return WinningNumbers.valueOf(
                Arrays.asList(1, 2, 3, 4, 5, 6), 9
        );
    }
}
