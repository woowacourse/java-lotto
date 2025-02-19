package lotto.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    void 입력값을_정상적으로_검증한다() {
        // Given
        final String input = "abc";

        // When & Then
        Assertions.assertThatCode(() -> {
            InputValidator.validate(input);
        }).doesNotThrowAnyException();
    }

    @Test
    void 입력값이_null이면_예외가_발생한다() {
        // Given
        final String input = null;

        // When & Then
        Assertions.assertThatThrownBy(() -> InputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\t"})
    void 입력값이_비어있으면_예외가_발생한다(final String input) {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> InputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값은 빈 값일 수 없습니다.");
    }
}
