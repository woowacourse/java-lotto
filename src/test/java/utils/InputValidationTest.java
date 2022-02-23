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

    @DisplayName("1000원 이하의 입력 들어오면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} price={0}")
    @ValueSource(strings = {"-10000", "-50000", "700", "999"})
    void checkUnderMinimumPriceInput_throwIllegalException(final String price) {
        assertThatThrownBy(() -> InputValidation.validatePrice(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 1000원 이상만 가능합니다.");
    }

    @DisplayName("입력된 번호가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} numbers={0}")
    @ValueSource(strings = {"12, 40, 43, 12,", "12, 4, 1, 2, 4, 5, 6"})
    void checkNumOfNumbers_throwIllegalException(final String numbers) {
        assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개의 번호를 입력해줘야 합니다.");
    }

    /**
     * + validateWinningNumber()
     * - checkNumberRange()
     * - checkNumOfNumber()
     * - checkDuplicateNumber()
     * + validateBonusNumber()
     * - checkNumberRange()
     * - checkDuplicateNumber()
     */

}
