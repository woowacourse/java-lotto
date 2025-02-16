package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputErrorMessage;

public class MoneyTest {
    @DisplayName("구매 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @ParameterizedTest()
    @ValueSource(ints = {1001, 20550})
    void throwsExceptionWhenPurchaseAmountIsNotDivisibleBy1000(int purchasedAmount) {
        // when & then
        Assertions.assertThatThrownBy(() -> {
                    new Money(purchasedAmount);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputErrorMessage.NOT_DIVIDED_BY_1000.getMessage());
    }
}
