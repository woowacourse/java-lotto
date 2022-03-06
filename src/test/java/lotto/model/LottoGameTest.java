package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.numbergenerator.LottoNumberGenerator;
import lotto.model.numbergenerator.ManualGenerator;

class LottoGameTest {

    @Test
    @DisplayName("수동 구매 검증 테스트")
    void buyManualLottosTest() {
        LottoGame lottoGame = new LottoGame(1000, 1, new TestNumberGenerator());
        List<Set<Integer>> rawManualLottos = List.of(Set.of(1, 2, 3, 11, 12, 13));

        Lottos manualLottos = lottoGame.buyManualLottos(rawManualLottos);
        List<Lotto> expected = List.of(new Lotto(Set.of(1, 2, 3, 11, 12, 13)));

        assertThat(manualLottos.getLottos().toString()).isEqualTo(expected.toString());
    }

    @Test
    @DisplayName("자동 구매 검증 테스트")
    void buyAutoLottosTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestNumberGenerator());

        List<Lotto> autoLottos = lottoGame.getAutoLottos();
        List<Lotto> expected = List.of(new Lotto(Set.of(1, 2, 3, 11, 12, 13)));

        assertThat(autoLottos.toString()).isEqualTo(expected.toString());
    }

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestNumberGenerator());
        assertThatThrownBy(() ->
            lottoGame.generateLottoResult(new Lottos(new ManualGenerator(Collections.emptyList()), 0),
                Set.of(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateYieldTest() {
        LottoGame lottoGame = new LottoGame(1000, 0, new TestNumberGenerator());
        LottoResult lottoResult = lottoGame.generateLottoResult(
            new Lottos(new ManualGenerator(Collections.emptyList()), 0),
            Set.of(1, 2, 3, 4, 5, 6),
            7);
        Yield yield = lottoGame.calculateYield(lottoResult);

        assertThat(yield.getYield()).isCloseTo(5.00f, Percentage.withPercentage(99));
    }

    static class TestNumberGenerator implements LottoNumberGenerator {

        @Override
        public Set<Integer> generate() {
            return Set.of(1, 2, 3, 11, 12, 13);
        }
    }
}
