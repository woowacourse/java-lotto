package domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserBalanceTest {

    @DisplayName("숫자가 아닌 입력 시 예외 발생")
    @Test
    void validateUserBalance_StringInput_ThrowsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserBalance("천원"))
                .withMessageMatching("구입금액은 숫자여야 합니다.");
    }

    @DisplayName("1000 미만이거나 1000의 배수가 아닌 입력 시 예외 발생")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(strings = {"-1000", "100", "1500"})
    void validateUserBalance_InvalidCurrency_ThrowsIllegalArgumentException(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new UserBalance(value))
                .withMessageMatching("구입금액은 1000원 이상이어야 하며 1000원 미만일 수 없습니다.");
    }
}
