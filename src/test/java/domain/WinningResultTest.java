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

class WinningResultTest {

    @ParameterizedTest
    @MethodSource("rewardParameterProvider")
    @DisplayName("당첨된 로또 결과를 생성하는 기능")
    void createResult(LottoReward reward, int rewardCount) {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.FIFTH);

        WinningResult winningResult = new WinningResult(purchaseMoney, lottoRewards);
        Map<LottoReward, Integer> results = winningResult.values();

        assertThat(results.get(reward)).isEqualTo(rewardCount);
    }

    private static Stream<Arguments> rewardParameterProvider() {
        return Stream.of(
            Arguments.of(LottoReward.FIRST, 2),
            Arguments.of(LottoReward.SECOND, 2),
            Arguments.of(LottoReward.FIFTH, 1)
        );
    }

    @Test
    @DisplayName("WinningResult 객체 생성 시 로또 구매 금액 값을 null 로 생성하려는 경우")
    void createWinningResultWithNullMoney() {
        List<LottoReward> lottoRewards = new ArrayList<>();
        lottoRewards.add(LottoReward.FIRST);
        lottoRewards.add(LottoReward.SECOND);
        lottoRewards.add(LottoReward.FIFTH);

        assertThatThrownBy(() ->
            new WinningResult(null, lottoRewards))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("WinningResult 객체 생성 시 로또 리워드 값을 null 로 생성하려는 경우")
    void createWinningResultWithNullReward() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);

        assertThatThrownBy(() ->
            new WinningResult(purchaseMoney, null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("당첨된 각 로또들의 초기 값 검증")
    void checkInitResult() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(5000);
        WinningResult winningResult = new WinningResult(purchaseMoney, new ArrayList<>());

        Map<LottoReward, Integer> Results = winningResult.values();

        assertThat(Results.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("당첨 결과의 수익률 계산 기능")
    void calculateProfitRate() {
        LottoGameMoney purchaseMoney = new LottoGameMoney(3000);
        List<LottoReward> lottoRewards = List.of(LottoReward.FIFTH, LottoReward.NONE, LottoReward.NONE);
        WinningResult winningResult = new WinningResult(purchaseMoney, lottoRewards);

        double profitRate = winningResult.profitRate();
        double expectedAnswer = (double)LottoReward.FIFTH.price() / purchaseMoney.amount();

        assertThat(profitRate).isEqualTo(expectedAnswer);
    }
}
