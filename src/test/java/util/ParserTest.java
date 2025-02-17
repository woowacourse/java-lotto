package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParserTest {

    @DisplayName("하나의 문자열 값을 숫자 값으로 변환하여 저장한다.")
    @Test
    void parseOneNumbers() {
        String numberInput = "1";
        assertThat(Parser.parseOneNumber(numberInput)).isEqualTo(1);
    }

    @DisplayName("정수가 아닌 문자열 값을 숫자 값으로 변환하는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "@", " ", "", "6.0"})
    void parseNotIntegerNumber(String numberInput) {
        assertThatThrownBy(() -> Parser.parseOneNumber(numberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("여러 개의 문자열 값을 공백을 제거하고 숫자 값으로 변환하여 저장한다.")
    @Test
    void parseMoreThanOneNumbers() {
        List<String> numbersInput = new ArrayList<>(Arrays.asList("0 ", " 1 ", "  2", "    3    ", "4", "   5"));
        List<Integer> parsedNumbers = Parser.parseNumbers(numbersInput);
        for (int index = 0; index < 6; index++) {
            assertThat(parsedNumbers.get(index)).isEqualTo(index);
        }
    }

    @DisplayName("여러 개의 문자열을 숫자로 변환할 때 정수가 아닌 값이 있는 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a, 1, 2, 3, 4, 5", "@, 1, 2, 3, 4, 5", "6.0, 1, 2, 3, 4, 5", " , 1, 2, 3, 4, 5",
            ", 1, 2, 3, 4, 5"})
    void parseNotIntegerNumbers(String winningNumberInput) {
        List<String> numbersInput = List.of(winningNumberInput.split(", "));
        assertThatThrownBy(() -> Parser.parseNumbers(numbersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("여러 개의 문자열을 숫자로 변환할 때 10자리 이상의 정수를 입력하는 경우 예외가 발생한다")
    @Test
    void parseNumbersOverLengthTen() {
        List<String> numbersInput = new ArrayList<>(Arrays.asList("10000000001"));
        assertThatThrownBy(() -> Parser.parseNumbers(numbersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
