package lotto.vaildator;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import lotto.constant.ExceptionMessage;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("입력값이 null인 경우 예외 발생")
    @Test
    void 입력값이_null인_경우_예외_발생() {
        String input = null;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateBlank(input))
                .withMessage(ExceptionMessage.INVALID_INPUT.getContent());
    }

    @DisplayName("입력값이 빈 값인 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void 입력값이_빈_값인_경우_예외_발생(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateBlank(input))
                .withMessage(ExceptionMessage.INVALID_INPUT.getContent());
    }

    @DisplayName("입력값이 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"우테코", "Landy"})
    void 입력값이_숫자가_아닐_경우_예외_발생(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateNumberFormat(input))
                .withMessage(ExceptionMessage.INVALID_NUMBER_FORMAT.getContent());
    }

    @DisplayName("입력된 구매 금액이 1000단위가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1500})
    void 입력된_구매_금액이_1000단위가_아닌_경우_예외_발생(int purchaseAmount) {
        String messageTemplate = ExceptionMessage.INVALID_PURCHASE_AMOUNT.getContent();
        String expectedMessage = String.format(messageTemplate, Lotto.LOTTO_PRICE);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validatePurchaseAmount(purchaseAmount))
                .withMessage(expectedMessage);
    }

    @DisplayName("당첨 번호가 숫자가 아닌 경우 예외 발생")
    @Test
    void 당첨_번호가_숫자가_아닌_경우_예외_발생() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateWinningNumbers(List.of("1", "2", "3", "4", "5", "육")))
                .withMessage(ExceptionMessage.INVALID_INPUT.getContent());
    }

    @DisplayName("보너스 번호가 입력된 당첨 번호와 중복되면 예외 발생")
    @Test
    void 보너스_번호가_입력된_당첨_번호와_중복되면_예외_발생() {
        Lotto inputLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> InputValidator.validateBonusNumber(inputLotto, bonusNumber))
                .withMessage(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
    }
}