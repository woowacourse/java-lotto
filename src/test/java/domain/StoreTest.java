package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StoreTest {

    @DisplayName("로또는 1개에 1000원이다.")
    @ParameterizedTest(name = "{index} {displayName} {0}원으로 구매한 로또 수 : {1}")
    @CsvSource(value = {"20000, 20", "18500, 18", "1500, 1"})
    void purchaseLottos(final int inputMoney, final int expected) {
        Lottos purchasedLotto = Store.purchaseLottos(inputMoney);

        assertThat(purchasedLotto.getLottos().size()).isEqualTo(expected);
    }

}