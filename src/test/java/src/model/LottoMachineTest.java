package src.model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import src.model.lotto.generator.MockedNumberGenerator;

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
}