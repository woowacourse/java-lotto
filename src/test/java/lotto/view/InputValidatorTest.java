package lotto.view;

import lotto.view.InputValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    void 정상_입력값이_들어오면_예외가_발생하지_않는다() {
        // Given
        String input = "비어있지 않은 입력";

        // When & Then
        Assertions.assertThatCode(() -> InputValidator.validateNullOrBlank(input))
                .doesNotThrowAnyException();
    }

    @Test
    void 입력값이_null이면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> InputValidator.validateNullOrBlank(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\t"})
    void 입력값이_비어있으면_예외가_발생한다(final String input) {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> InputValidator.validateNullOrBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 빈 값일 수 없습니다.");
    }
}
