package domain.bettingMoney;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("Budget 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 14000})
    void Budget_생성_테스트(int value) {
        assertThatCode(() -> BettingMoney.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 숫자가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void Budget_생성_예외_테스트(int value) {
        assertThatThrownBy(() -> BettingMoney.of(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}