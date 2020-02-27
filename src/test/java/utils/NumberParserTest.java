package utils;

import Lotto.utils.NumberParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberParserTest {
    @ParameterizedTest
    @DisplayName("정상 입력 파싱 테스트")
    @MethodSource("generateRightInput")
    void rightInput(String input, List<Integer> expectedNumbers) {
        assertThat(NumberParser.parseIntoLottoNumbers(input)).isEqualTo(expectedNumbers);
    }

    static Stream<Arguments> generateRightInput() {
        return Stream.of(
                Arguments.of("  1  ,6,   3, 55 ,, 77", Arrays.asList(1,6,3,55,77)),
                Arguments.of("1 ,2 ,3 ,4 ,5 , 6", Arrays.asList(1,2,3,4,5,6)));
    }

    @ParameterizedTest
    @DisplayName("잘못된 입력 파싱 Exception 테스트")
    @MethodSource("generateWrongInput")
    void wrongInput(String input) {
        assertThatThrownBy(() -> NumberParser.parseIntoLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    static Stream<Arguments> generateWrongInput() {
        return Stream.of(
                Arguments.of("1, a, d,1,43,6"),
                Arguments.of("1,2,3,|,99"));
    }
}
