package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static lotto.view.NumberParser.INVALID_BONUS_NUMBER_FORMAT_MESSAGE;

import lotto.exception.InvalidNumberRangeException;
import lotto.model.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberParserTest {

    @ParameterizedTest
    @CsvSource({"1, 1", "24, 24", "42, 42"})
    @DisplayName("정상적인 숫자 입력 처리")
    void parse(String text, int actual) {
        NumberParser parser = new NumberParser();
        assertThat(parser.parse(text)).isEqualTo(new Number(actual));
    }

    @ParameterizedTest
    @DisplayName("비정상적인 숫자 입력 처리")
    @ValueSource(strings = {"00", "05", "0", "-3", "클레이"})
    void invalidParse(String text) {
        NumberParser parser = new NumberParser();
        assertThatThrownBy(() -> parser.parse(text))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource({"46", "100"})
    @DisplayName("비정상적인 로또 번호 입력 처리")
    void invalidLottoNumberParse(String text) {
        NumberParser parser = new NumberParser();
        assertThatThrownBy(() -> parser.parse(text))
            .isInstanceOf(InvalidNumberRangeException.class);
    }
}
