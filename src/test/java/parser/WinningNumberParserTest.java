package parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.parser.WinningNumberParser;

public class WinningNumberParserTest {
    @DisplayName("당첨번호_중복예외_테스트")
    @Test
    void 당첨번호_중복예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1, 2, 3, 4, 6, 6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.DUPLICATE_EXIST.getMessage());
    }

    @DisplayName("당첨넘버_범위예외_테스트")
    @Test
    void 당첨넘버_범위예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1, 2, 3, 4, 6, 46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("당첨넘버_갯수예외_테스트")
    @Test
    void 당첨넘버_갯수예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.LOTTO_NUMBER_COUNT.getMessage());
    }

    @DisplayName("당첨넘버_문자예외_테스트")
    @Test
    void 당첨넘버_문자예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1, 2, 3, 4, 5, a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.NOT_NUMBER.getMessage());
    }
}