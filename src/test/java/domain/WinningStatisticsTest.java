package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningStatisticsTest {

    @ParameterizedTest
    @MethodSource("rewardParameterProvider")
    @DisplayName("당첨된 로또들의 통계를 생성하는 기능")
    void createStatistics_02(LottoReward reward, int rewardCount) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.FIFTH);

        WinningStatistics winningStatistics = new WinningStatistics(lottoRewards);
        Map<LottoReward, Integer> statistics = winningStatistics.getWinningStatistics();

        assertThat(statistics.get(reward)).isEqualTo(rewardCount);
    }

    private static Stream<Arguments> rewardParameterProvider() {
        return Stream.of(
            Arguments.of(LottoReward.FIRST, 2),
            Arguments.of(LottoReward.SECOND, 2),
            Arguments.of(LottoReward.FIFTH, 1)
        );
    }

    @Test
    @DisplayName("WinningStatistics 객체를 null 로 생성하려는 경우")
    void createWinningStatisticsWithNull() {
        assertThatThrownBy(() ->
            new WinningStatistics(null))
            .isInstanceOf(NullPointerException.class);
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
