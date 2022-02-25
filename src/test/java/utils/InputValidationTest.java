package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("올바르지 않은 입력값이 들어올 시 예외를 발생시킨다.")
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

    @Nested
    @DisplayName("로또 넘버 입력 테스트")
    class WinningNumberTest {

        @DisplayName("입력된 번호가 6개가 아니면 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} numbers={0}")
        @ValueSource(strings = {"12, 40, 43, 12,", "12, 4, 1, 2, 4, 5, 6"})
        void checkNumOfNumbers_throwIllegalException(final String numbers) {
            assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 6개의 번호를 입력해줘야 합니다.");
        }

        @DisplayName("로또 번호가 숫자가 아닌경우 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} numbers={0}")
        @ValueSource(strings = {"12, 안녕, 43, 12, 1, 3", "12, 4, 1, qwe, 4, 5"})
        void checkNonNumbers_throwIllegalException(final String numbers) {
            assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
        }

        @DisplayName("로또 번호가 범위를 벗어난 경우 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} numbers={0}")
        @ValueSource(strings = {"1, 2, 3, 55, 43, 45", "-1, 2, 3, 4, 5, 6", "0, 2, 3, 4, 5, 6"})
        void checkNumberRange_throwIllegalException(final String numbers) {
            assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.");
        }

        @DisplayName("로또 번호가 중복된 경우 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} numbers={0}")
        @ValueSource(strings = {"1, 1, 3, 4, 43, 45", "1, 2, 3, 4, 6, 6"})
        void checkDuplicateNumber_throwIllegalException(final String numbers) {
            assertThatThrownBy(() -> InputValidation.validateWinningNumber(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복되면 안 됩니다.");
        }
    }

    @Nested
    @DisplayName("보너스 번호 테스트")
    class BonusTest {

        @DisplayName("보너스 번호가 숫자가 아닌 경우 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} bonus={0}")
        @ValueSource(strings = {"보너스", "넘버"})
        void checkBonusNonInteger_throwIllegalException(final String bonus) {
            assertThatThrownBy(() -> InputValidation.validateBonusNumber(bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 숫자만 입력해줘야 합니다.");
        }

        @DisplayName("보너스 번호가 범위를 벗어난 경우 예외가 발생한다.")
        @ParameterizedTest(name = "{index} {displayName} bonus={0}")
        @ValueSource(strings = {"0", "-1", "46"})
        void checkBonusOutRange_throwIllegalException(final String bonus) {
            assertThatThrownBy(() -> InputValidation.validateBonusNumber(bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1에서 45 사이의 값을 입력해줘야 합니다.");
        }
    }

}
