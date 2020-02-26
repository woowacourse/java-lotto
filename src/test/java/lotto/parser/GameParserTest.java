package lotto.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameParserTest {

    @Test
    @DisplayName("gameParser는 문자열을 입력받아 정수로 변환")
    void parseStringToInt() {
        String number = "1000";
        GameParser gameParser = new GameParser();
        assertThat(gameParser.parseInputToInt(number)).isEqualTo(1000);
    }

    @Test
    @DisplayName("정수 변환시 숫자가 아닌 문자열에 대해 예외 발생")
    void parseStringToInputThrowsException() {
        String notNumber = "피곤해요1";
        GameParser gameParser = new GameParser();
        assertThatThrownBy(() -> gameParser.parseInputToInt(notNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자열을 입력받아 ,로 분류하여 정수 셋을 반환")
    void parseStringToIntegerSet() {
        String inputNumbers = "1,2,3,4,5,6";
        GameParser gameParser = new GameParser();
        Set<Integer> numbers = gameParser.parseInputToNumbers(inputNumbers);
        assertThat(numbers).size().isEqualTo(6);
    }
}
