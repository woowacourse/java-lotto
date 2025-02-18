package view;

import static org.junit.jupiter.api.Assertions.*;

import exception.CommonExceptionType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("유효한 입력값이 들어왔을 때 List<Integer> 형태로 분리 되는 지 검증")
    void getIntegerInput() {
        // given & when
        InputParser inputParser = InputParser.of("1, 2, 3");
        List<Integer> expected = List.of(1, 2, 3);

        // then
        Assertions.assertThat(expected).containsAll(inputParser.getInputs());
    }

    @Test
    @DisplayName("숫자가 아닌 입력값이 들어왔을 떄 예외 처리")
    void invalidInput() {
        Assertions.assertThatThrownBy(
                        () -> InputParser.of("1, ㅁ, 3")
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_NUMBER_FORMAT.getMessage());
    }

    @Test
    @DisplayName("인자 갯수가 1개이길 예상했으나 여러개 일 때 예외 처리")
    void getInput() {
        InputParser inputParser = InputParser.of("1, 2, 3");
        Assertions.assertThatThrownBy(
                        inputParser::getInput
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_ARGUMENTS_SIZE.getMessage(1));
    }
}