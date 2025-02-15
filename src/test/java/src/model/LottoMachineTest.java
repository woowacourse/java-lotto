package src.model;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
}