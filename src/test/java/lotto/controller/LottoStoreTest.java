package lotto.controller;

import lotto.domain.BonusBall;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    private static final Lotto lotto = Lotto.from("1,2,3,4,5,6");
    private static final BonusBall bonusBall = new BonusBall(7, lotto);
    private static final WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

    private static Stream<Arguments> provideLottoAndProfitRateResult() {
        return Stream.of(
                Arguments.of(Lotto.from("2, 4, 8, 9, 13, 25"), 0.00),
                Arguments.of(Lotto.from("2, 4, 6, 8, 13, 25"), 5.00),
                Arguments.of(Lotto.from("2, 4, 6, 1, 7, 3"), 30000.00),
                Arguments.of(Lotto.from("1, 2, 3, 4, 5, 6"), 200000.00)
        );
    }

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("provideLottoAndProfitRateResult")
    void lottoProfitCalculateTest(Lotto inputLotto, double expectedProfitRate) {
        LottoRank lottoResult = winningLotto.getLottoResult(inputLotto);
        assertThat((double) (lottoResult.getPrizeMoney() / 1000)).isEqualTo(expectedProfitRate);
    }
}