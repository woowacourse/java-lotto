package util;

import static constant.ExceptionMessages.INVALID_CURRENCY_EXCEPTION_MESSAGE;
import static constant.ExceptionMessages.INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;
import static util.UserBalanceValidator.validateUserBalance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserBalanceValidatorTest {

    public static final String PARAMETERIZED_TEST_DISPLAY_FORMAT =
            DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]";

    @Test
    void validateUserBalance_StringInput_ThrowsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateUserBalance("천원"))
                .withMessageMatching(INVALID_NUMBER_INPUT_EXCEPTION_MESSAGE);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(strings = {"-1000", "100", "1500"})
    void validateUserBalance_InvalidCurrency_ThrowsIllegalArgumentException(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validateUserBalance(value))
                .withMessageMatching(INVALID_CURRENCY_EXCEPTION_MESSAGE);
    }
}
