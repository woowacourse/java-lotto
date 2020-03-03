package domain;

import Lotto.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class EarningRateTest {
    private static final LottoNumber ONE = new LottoNumber(1);
    private static final LottoNumber TWO = new LottoNumber(2);
    private static final LottoNumber THREE = new LottoNumber(3);
    private static final LottoNumber FOUR = new LottoNumber(4);
    private static final LottoNumber FIVE = new LottoNumber(5);
    private static final LottoNumber SIX = new LottoNumber(6);
    private static final LottoNumber SEVEN = new LottoNumber(7);
    private static final LottoNumber EIGHT = new LottoNumber(8);
    private static final LottoNumber NINE = new LottoNumber(9);
    private static final LottoNumber TEN = new LottoNumber(10);

    @Test
    @DisplayName("수익률 계산이 잘 되는지 확인")
    void calculateEarningRate() {
        Lotto winningLotto = new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX));
        WinningNumber winningNumber = new WinningNumber(winningLotto, SEVEN);

        Lottos inputLotto = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(ONE, TWO, THREE, FOUR, EIGHT, NINE)),
                new Lotto(Arrays.asList(ONE, TWO, THREE, EIGHT, NINE, TEN))
        ));

        Ranks ranks = new Ranks(winningNumber, inputLotto);
        PurchaseAmount purchaseAmount = new PurchaseAmount("10000");
        EarningRate earningRate = new EarningRate(ranks, purchaseAmount);
        assertThat(earningRate.getEarningRate()).isEqualTo(550.0);
    }
}
