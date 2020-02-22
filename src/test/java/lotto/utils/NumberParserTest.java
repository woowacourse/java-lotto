package lotto.utils;

import lotto.exceptions.NotNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberParserTest {
    @ParameterizedTest
    @MethodSource("generateRightInput")
    @DisplayName("파싱 잘되는것만 테스트")
    void winningNumberParseTest(String input, List<Integer> expectedNumbers) {
        assertThat(NumberParser.winningNumberParse(input))
                .isEqualTo(expectedNumbers);
    }

    static Stream<Arguments> generateRightInput() {
        return Stream.of(Arguments.of("1,2,3,4,5,6", Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @ParameterizedTest
    @MethodSource("generateWrongInput")
    @DisplayName("파싱 안되는것만 테스트")
    void wrongWinningNumberParseTest(String input) {
        assertThatThrownBy(() -> NumberParser.winningNumberParse(input))
                .isInstanceOf(NotNumberException.class)
                .hasMessageContaining("숫자만 입력하세요");
    }

    static Stream<Arguments> generateWrongInput() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6a"),
                Arguments.of(""),
                Arguments.of("1,,2,,3,,4")
        );
    }
}