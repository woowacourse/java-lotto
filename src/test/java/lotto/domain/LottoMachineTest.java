package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.utility.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private LottoMachine lottoMachine;

    @BeforeEach
    void beforeEach() {
        lottoMachine = new LottoMachine(new RandomGenerator());
    }

    @DisplayName("구매 금액에 해당하는 로또를 구매할 수 있다.")
    @Test
    void 구매_금액에_해당하는_로또를_구매할_수_있다() {
        int givenPurchaseAmount = 10000;

        List<Lotto> actualLottos = lottoMachine.purchaseLotto(givenPurchaseAmount);

        assertThat(actualLottos).hasSize(givenPurchaseAmount / Lotto.LOTTO_PRICE);
    }

    @DisplayName("발행된 로또의 당첨 등수를 찾을 수 있다.")
    @Test
    void 발행된_로또의_당첨_등수를_찾을_수_있다() {
        List<Lotto> givenLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)), new Lotto(List.of(8, 9, 10, 11, 12, 13)));
        WinningLotto givenWinningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        List<WinningTier> expectedWiningTier = List.of(
                WinningTier.FIRST, WinningTier.SECOND, WinningTier.THIRD, WinningTier.FOURTH, WinningTier.FIFTH);

        List<WinningTier> actualTiers = lottoMachine.findWinningTiers(givenLottos, givenWinningLotto);

        assertThat(actualTiers).containsExactlyInAnyOrderElementsOf(expectedWiningTier);
    }

    @DisplayName("수익률을 올바르게 계산할 수 있다.")
    @Test
    void 수익률을_올바르게_계산할_수_있다() {
        List<WinningTier> winningTiers = List.of(WinningTier.FOURTH, WinningTier.FIFTH, WinningTier.EMPTY);
        int purchaseAmount = 5000;
        double expectedProfit = 11;

        assertThat(lottoMachine.calculateProfit(winningTiers, purchaseAmount)).isEqualTo(expectedProfit);
    }
}