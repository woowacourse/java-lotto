package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoGameTest {

    private LottoGameMoney purchaseMoney;
    private Lottos lottos;

    @BeforeEach
    void initLottos() {
        purchaseMoney = new LottoGameMoney(8000);

        Lotto lotto1 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 16));
        Lotto lotto2 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 9));
        Lotto lotto3 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 8));
        Lotto lotto4 = LottoFactory.createLotto(List.of(1, 2, 3, 4, 44, 6));
        Lotto lotto5 = LottoFactory.createLotto(List.of(1, 2, 3, 14, 27, 38));
        Lotto lotto6 = LottoFactory.createLotto(List.of(1, 2, 23, 14, 27, 38));
        Lotto lotto7 = LottoFactory.createLotto(List.of(1, 22, 23, 14, 27, 38));
        Lotto lotto8 = LottoFactory.createLotto(List.of(11, 22, 23, 14, 27, 38));

        lottos = new Lottos(List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6, lotto7, lotto8));
    }

    @Test
    @DisplayName("LottoGame 을 정상적으로 생성하는 경우")
    void createLottoGame() {
        LottoGame lottoGame = new LottoGame(purchaseMoney, lottos);

        assertThat(lottoGame).isNotNull();
    }

    @Test
    @DisplayName("LottoGame 생성시 구매금액을 null 로 생성하려는 경우")
    void createLottoGameWithNullMoney() {
        assertThatThrownBy(() ->
            new LottoGame(null, lottos))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("LottoGame 생성시 로또들을 null 로 생성하려는 경우")
    void createLottoGameWithNullLottos() {
        assertThatThrownBy(() ->
            new LottoGame(purchaseMoney, null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("로또 게임 결과 통계를 계산하는 경우")
    void calculateWinningStatistics(LottoReward reward, int rewardCount) {
        LottoGame lottoGame = new LottoGame(purchaseMoney, lottos);
        Lotto lotto = LottoFactory.createLotto(List.of(1, 2, 3, 4, 17, 16));
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(9);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusLottoNumber);

        WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics(winningLotto);

        assertThat(winningStatistics.getWinningStatistics().get(reward)).isEqualTo(rewardCount);
    }

    private static Stream<Arguments> parameterProvider() {
        return Stream.of(
            Arguments.of(LottoReward.FIRST, 1),
            Arguments.of(LottoReward.SECOND, 1),
            Arguments.of(LottoReward.THIRD, 1),
            Arguments.of(LottoReward.FOURTH, 1),
            Arguments.of(LottoReward.FIFTH, 1),
            Arguments.of(LottoReward.NONE, 3)
        );
    }
}
