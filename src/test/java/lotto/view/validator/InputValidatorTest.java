package lotto.view.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import lotto.exception_message.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    InputValidator inputValidator;

    @BeforeEach
    void beforeEach() {
        inputValidator = new InputValidator();
    }

    @DisplayName("비어있는 값이 입력되는 것을 검증할 수 있다.")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void 비어있는_값이_입력되는_것을_검증할_수_있다(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidator.validateBlank(input))
                .withMessage(ExceptionMessage.INVALID_INPUT.getContent());
    }

    @DisplayName("하나의 문자열이 숫자형인지 검증할 수 있다.")
    @ParameterizedTest
    @CsvSource({"-1,true", "0,true", "1,true", "+2,true", "삼,false", " four,false"})
    void 비어있는_값이_입력되는_것을_검증할_수_있다(String input, boolean isExceptionOccurred) {
        if (isExceptionOccurred) {
            assertThatCode(() -> inputValidator.validateNumberFormat(input))
                    .doesNotThrowAnyException();
            return;
        }

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidator.validateNumberFormat(input))
                .withMessage(ExceptionMessage.INVALID_NUMBER_FORMAT.getContent());
    }

    @DisplayName("여러개의 문자열이 숫자형인지 검증할 수 있다.")
    @ParameterizedTest
    @MethodSource
    void 여러개의_문자열이_숫자형인지_검증할_수_있다(List<String> inputs, boolean isExceptionOccurred) {
        if (isExceptionOccurred) {
            assertThatCode(() -> inputValidator.validateNumberFormat(inputs))
                    .doesNotThrowAnyException();
            return;
        }

        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputValidator.validateNumberFormat(inputs))
                .withMessage(ExceptionMessage.INVALID_NUMBER_FORMAT.getContent());
    }

    static Stream<Arguments> 여러개의_문자열이_숫자형인지_검증할_수_있다() {
        return Stream.of(
                Arguments.of(List.of("1", "2", "3"), true),
                Arguments.of(List.of("-1", "+2", "3"), true),
                Arguments.of(List.of("1", "2", "삼"), false)
        );
    }
}