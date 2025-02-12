package lotto.vaildator;

import lotto.costant.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LogicValidatorTest {
    @DisplayName("리스트 내에 중복되는 번호가 있으면 예외 발생")
    @Test
    void 리스트_내에_중복되는_번호가_있으면_예외_발생() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LogicValidator.validateDuplication(List.of(1, 1, 2, 3, 4, 5)))
                .withMessage(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
    }

    @DisplayName("리스트 내 숫자가 범위를 벗어난 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 리스트_내_숫자가_범위를_벗어난_경우_예외_발생(int invalidNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LogicValidator.validateRange(List.of(1, 2, 3, 4, 5, invalidNumber), 1, 45))
                .withMessage(ExceptionMessage.OUT_OF_RANGE.getContent());
    }

    @DisplayName("단일 숫자가 범위를 벗어난 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 단일_숫자가_범위를_벗어난_경우_예외_발생(int invalidNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LogicValidator.validateRange(invalidNumber, 1, 45))
                .withMessage(ExceptionMessage.OUT_OF_RANGE.getContent());
    }

    @DisplayName("리스트 크기가 잘못된 경우 예외 발생")
    @Test
    void 리스트_크기가_잘못된_경우_예외_발생() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LogicValidator.validateSize(List.of(1, 2, 3, 4, 5, 6, 7), 6))
                .withMessage(ExceptionMessage.INVALID_NUMBER_COUNT.getContent());
    }
}
