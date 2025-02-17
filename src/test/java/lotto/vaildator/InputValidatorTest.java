package lotto.vaildator;

import lotto.validator.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class InputValidatorTest {

    @DisplayName("입력값이 null인 경우 예외 발생")
    @Test
    void 입력값이_null인_경우_예외_발생() {
        String input = null;

        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateBlank(input));
    }

    @DisplayName("입력값이 빈 값인 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void 입력값이_빈_값인_경우_예외_발생(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateBlank(input));
    }

    @DisplayName("입력값이 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"우테코", "Landy"})
    void 입력값이_숫자가_아닐_경우_예외_발생(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateNumberFormat(input));
    }

    @DisplayName("당첨 번호가 숫자가 아닌 경우 예외 발생")
    @Test
    void 당첨_번호가_숫자가_아닌_경우_예외_발생() {
        List<String> strings = List.of("1", "2", "3", "4", "5", "육");

        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateWinningNumbers(strings));
    }
}
