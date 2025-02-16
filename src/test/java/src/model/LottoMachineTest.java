package src.model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import src.model.lotto.Lotto;
import src.model.lotto.generator.MockedNumberGenerator;
import src.model.lotto.generator.NumberGenerator;
import src.model.winning_lotto.WinningLotto;

class LottoMachineTest {

    private final MockedNumberGenerator mockedNumberGenerator = new MockedNumberGenerator();
    private final LottoMachine lottoMachine = new LottoMachine(mockedNumberGenerator);

    @ParameterizedTest
    @ValueSource(ints = {1_001, 999, 2_100})
    void 로또_구매_금액이_나누어떨어지지_않으면_예외가_발생한다(int purchaseMoney) {

        assertThatIllegalArgumentException().isThrownBy(() -> lottoMachine.issueLottos(purchaseMoney));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1_000, -2_000, -30000})
    void 로또_구매_금액이_음수면_예외가_발생한다(int purchaseMoney) {

        assertThatIllegalArgumentException().isThrownBy(() -> lottoMachine.issueLottos(purchaseMoney));
    }

    @Test
    void 당첨_시_수익률_계산_테스트() {
        int purchaseMoney = 10000;

        List<LottoPrize> prizes = Arrays.asList(
                LottoPrize.SIX,
                LottoPrize.FIVE_WITH_BONUS,
                LottoPrize.FIVE
        );
        double totalPrize = LottoPrize.SIX.getPrize() +
                LottoPrize.FIVE_WITH_BONUS.getPrize() +
                LottoPrize.FIVE.getPrize();
        double expectedProfitRate = Math.round((totalPrize / purchaseMoney) * 100) / 100.0;

        double actualProfitRate = lottoMachine.calculateProfitRate(prizes, purchaseMoney);
        assertEquals(expectedProfitRate, actualProfitRate);
    }

    @Test
    void 로또_결과_정상_반환_테스트() {
        MockedNumberGenerator generator1 = new MockedNumberGenerator();
        generator1.setNumbersToGenerate(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto1 = Lotto.generateFrom(generator1);

        MockedNumberGenerator generator2 = new MockedNumberGenerator();
        generator2.setNumbersToGenerate(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto2 = Lotto.generateFrom(generator2);

        MockedNumberGenerator generator3 = new MockedNumberGenerator();
        generator3.setNumbersToGenerate(Arrays.asList(10, 11, 12, 13, 14, 15));
        Lotto lotto3 = Lotto.generateFrom(generator3);

        List<Lotto> lottos = Arrays.asList(lotto1, lotto2, lotto3);

        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        LottoMachine lottoMachine = new LottoMachine(new MockedNumberGenerator());
        List<LottoPrize> results = lottoMachine.getLottoResults(lottos, winningLotto);

        assertNotNull(results);
        assertEquals(3, results.size());

        assertEquals(LottoPrize.SIX, results.get(0));
        assertEquals(LottoPrize.FIVE_WITH_BONUS, results.get(1));
        assertEquals(LottoPrize.ZERO, results.get(2));
    }
}