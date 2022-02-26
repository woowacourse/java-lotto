package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("올바르지 않은 입력값이 들어올시 예외를 발생시킨다.")
public class InputValidationTest {

    @Nested
    @DisplayName("구매금액 입력 테스트")
    class PriceTest {

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
    }

    @DisplayName("로또에 숫자가 아닌 입력이 들어왔을 때 예외 발생")
    @ParameterizedTest(name ="{displayName} input={0} ")
    @ValueSource(strings = {"12, 안녕, 43, 12, 1, 3", "12, 4, 1, qwe, 4, 5"})
    void checkNonNumbers_throwIllegalException(final String numbers) {
        assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
    }

    @DisplayName("보너스로 숫자가 아닌 입력이 들어왔을 때 예외 발생")
    @ParameterizedTest(name = "{displayName} input={0}")
    @ValueSource(strings = {"보너스", ".", "bonus", "0.8"})
    void checkBonusNonInteger_throwIllegalException(final String bonus) {
        assertThatThrownBy(() -> InputValidation.validateBonusNumber(bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
    }

}
