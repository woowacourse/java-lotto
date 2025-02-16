package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.exception_message.ExceptionMessage;
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

    @DisplayName("단위에 맞지 않는 구매 금액이 입력될 경우 예외가 발생한다.")
    @Test
    void 단위에_맞지_않는_구매_금액이_입력될_경우_예외가_발생한다() {
        String messageTemplate = ExceptionMessage.INVALID_PURCHASE_AMOUNT.getContent();
        String expectedMessage = String.format(messageTemplate, Lotto.LOTTO_PRICE);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoMachine.purchaseLotto(1010))
                .withMessage(expectedMessage);
    }

    @DisplayName("발행된 로또의 당첨 등수를 찾을 수 있다.")
    @Test
    void 발행된_로또의_당첨_등수를_찾을_수_있다() {
        List<Lotto> givenLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)), new Lotto(List.of(8, 9, 10, 11, 12, 13)));
        WinningLotto givenWinningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
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