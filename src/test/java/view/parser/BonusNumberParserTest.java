package view.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static view.parser.BonusNumberParser.INVALID_BONUS_NUMBER_FORMAT_MESSAGE;

import exception.InvalidRangeLottoNumberException;
import model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberParserTest {

    @ParameterizedTest
    @CsvSource({"1, 1", "24, 24", "42, 42"})
    @DisplayName("정상적인 숫자 입력 처리")
    void parse(String text, int actual) {
        BonusNumberParser parser = new BonusNumberParser();
        assertThat(parser.parse(text)).isEqualTo(new LottoNumber(actual));
    }

    @ParameterizedTest
    @DisplayName("비정상적인 숫자 입력 처리")
    @ValueSource(strings = {"00", "05", "0", "-3", "클레이"})
    void invalidParse(String text) {
        BonusNumberParser parser = new BonusNumberParser();
        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource({"46", "100"})
    @DisplayName("비정상적인 로또 번호 입력 처리")
    void invalidLottoNumberParse(String text) {
        BonusNumberParser parser = new BonusNumberParser();
        assertThatThrownBy(() ->parser.parse(text))
                .isInstanceOf(InvalidRangeLottoNumberException.class);
    }
}
