package domain;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ExceptionMessage;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.RandomGenerator;

class LottosTest {

    @DisplayName("정상 구매 개수 테스트")
    @ParameterizedTest
    @CsvSource({"14000,14", "1000,1", "1500000,1500"})
    void buyLottoTest(int money, int expectedCount) {
        RandomNumberGenerate randomNumberGenerate = new RandomNumberGenerate();
        Lottos lottos = new Lottos(money, randomNumberGenerate);
        assertThat(lottos.getLottos().size()).isEqualTo(expectedCount);
    }

    @DisplayName("금액 범위 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void buyLottoRangeExceptionTest(int money) {
        RandomNumberGenerate randomNumberGenerate = new RandomNumberGenerate();
        assertThatThrownBy(() -> new Lottos(money, randomNumberGenerate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PRICE_RANGE_ERROR.getMessage());
    }

    @DisplayName("금액 단위 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 1103, 15001})
    void buyLottoUnitExceptionTest(int money) {
        RandomNumberGenerate randomNumberGenerate = new RandomNumberGenerate();
        assertThatThrownBy(() -> new Lottos(money, randomNumberGenerate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.PRICE_UNIT_ERROR.getMessage());
    }

    static class RandomNumberGenerate implements RandomGenerator {
        @Override
        public int generate() {
            Random random = new Random();
            return random.nextInt(LOTTO_RANGE_MAX.getValue() - LOTTO_RANGE_MIN.getValue() + 1)
                    + LOTTO_RANGE_MIN.getValue();
        }
    }
}