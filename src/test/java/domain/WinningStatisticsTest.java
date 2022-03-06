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
    void createStatistics(LottoReward reward, int rewardCount) {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.FIFTH);

        WinningStatistics winningStatistics = new WinningStatistics(purchaseMoney, lottoRewards);
        Map<LottoReward, Integer> statistics = winningStatistics.values();

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
    @DisplayName("WinningStatistics 객체 생성 시 로또 구매 금액 값을 null 로 생성하려는 경우")
    void createWinningStatisticsWithNullMoney() {
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.FIFTH);

        assertThatThrownBy(() ->
            new WinningStatistics(null, lottoRewards))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("WinningStatistics 객체 생성 시 로또 리워드 값을 null 로 생성하려는 경우")
    void createWinningStatisticsWithNullReward() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);

        assertThatThrownBy(() ->
            new WinningStatistics(purchaseMoney, null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("당첨된 각 로또들의 초기 값 검증")
    void checkInitStatistics() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);
        WinningStatistics winningStatistics = new WinningStatistics(purchaseMoney, new ArrayList<>());

        Map<LottoReward, Integer> statistics = winningStatistics.values();

        assertThat(statistics.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("당첨 통계의 수익률 계산 기능")
    void calculateProfitRate() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(3000);
        List<LottoReward> lottoRewards = List.of(LottoReward.FIFTH, LottoReward.NONE, LottoReward.NONE);
        WinningStatistics winningStatistics = new WinningStatistics(purchaseMoney, lottoRewards);

        double profitRate = winningStatistics.calculateProfitRate();
        double expectedAnswer = (double)LottoReward.FIFTH.price() / purchaseMoney.amount();

        assertThat(profitRate).isEqualTo(expectedAnswer);
    }
}
