package domain.bettingMoney;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("BettingMoney 정상 생성테스트.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 14000})
    void bettingMoneyGenerateTest(int value) {
        assertThatCode(() -> BettingMoney.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 숫자(로또 티켓 구입 금액보다 큰 양수의 숫자))가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 900})
    void bettingMoneyNotGuaranteedErrorTest(int value) {
        assertThatThrownBy(() -> BettingMoney.of(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}