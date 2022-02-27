package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseMoneyValidatorTest {
    private static final String NOT_MULTIPLES_OF_1000_ERROR_MESSAGE = "금액을 1,000의 배수로 입력해주세요.";
    private static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";

    @Test
    @DisplayName("구입 금액이 1000의 배수가 아닐 경우 IllegalArgumentException 오류를 발생한다.")
    void checkNotMultiplesOf1000Input() {
        assertThatThrownBy(() -> PurchaseMoneyValidator.validate(1700))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_MULTIPLES_OF_1000_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("구입 금액이 양수가 아닐 경우 IllegalArgumentException 오류를 발생한다.")
    void checkNegativeInput() {
        assertThatThrownBy(() -> PurchaseMoneyValidator.validate(-17000))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_POSITIVE_ERROR_MESSAGE);
    }
}
