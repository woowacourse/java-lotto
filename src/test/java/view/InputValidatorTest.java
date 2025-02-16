package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    InputValidator inputValidator;

    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }

    @DisplayName("구매금액이 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "", "1000원", " "})
    void purchaseAmountUnitTest(String purchaseAmount) {
        assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨번호가 콤마로 구분한 6개의 요소가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3", "1.2.3.4.5.6"})
    void winningNumberSizeTest(String winningNumber) {
        assertThatThrownBy(() -> inputValidator.validateWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨번호가 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,abc", "!@#,1,2,3,4,5"})
    void winningNumberNotNumberTest(String winningNumber) {
        assertThatThrownBy(() -> inputValidator.validateWinningNumber(winningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "!@#"})
    void bonusNumberInputTest(String bonus) {
        assertThatThrownBy(() -> inputValidator.validateBonusInput(bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}