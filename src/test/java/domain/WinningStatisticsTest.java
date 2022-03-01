package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("당첨된 각 로또들의 통계를 생성하는 기능 1")
    void createStatistics_01() {
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);

        WinningStatistics winningStatistics = new WinningStatistics(lottoRewards);
        Map<LottoReward, Integer> statistics = winningStatistics.getWinningStatistics();

        assertThat(statistics.get(LottoReward.FIRST)).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨된 각 로또들의 통계를 생성하는 기능 2")
    void createStatistics_02() {
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);

        WinningStatistics winningStatistics = new WinningStatistics(lottoRewards);
        Map<LottoReward, Integer> statistics = winningStatistics.getWinningStatistics();

        assertThat(statistics.get(LottoReward.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨된 각 로또들의 초기 값 검증")
    void checkInitStatistics() {
        WinningStatistics winningStatistics = new WinningStatistics(new ArrayList<>());

        Map<LottoReward, Integer> statistics = winningStatistics.getWinningStatistics();

        assertThat(statistics.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("당첨 통계의 수익률 계산 기능")
    void calculateProfitRate() {
        final int purchaseLottoAmount = 3000;
        List<LottoReward> lottoRewards = List.of(LottoReward.FIFTH, LottoReward.NONE, LottoReward.NONE);
        WinningStatistics winningStatistics = new WinningStatistics(lottoRewards);

        double profitRate = winningStatistics.calculateProfitRate();
        double expectedAnswer = (double)LottoReward.FIFTH.getPrice() / purchaseLottoAmount;

        assertThat(profitRate).isEqualTo(expectedAnswer);
    }
}
