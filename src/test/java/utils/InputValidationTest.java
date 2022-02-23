package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputValidationTest {

    @DisplayName("정수가 아닌 숫자가 들어오면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} price={0}")
    @ValueSource(strings = {"qwe", "asd"})
    void checkNonIntegerPriceInput_throwIllegalException(final String price) {
        assertThatThrownBy(() -> InputValidation.validatePrice(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 정수만 가능합니다.");
    }

}
