package parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.parser.WinningNumberParser;

public class WinningNumberParserTest {
    @DisplayName("당첨번호_중복예외_테스트")
    @Test
    void 당첨번호_중복예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1,2,3,4,6,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 내용이 존재합니다.");
    }

    @DisplayName("당첨넘버_범위예외_테스트")
    @Test
    void 당첨넘버_범위예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1,2,3,4,6,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 숫자가 1~45의 유효 범위를 벗어납니다.");
    }

    @DisplayName("당첨넘버_갯수예외_테스트")
    @Test
    void 당첨넘버_갯수예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 구성 숫자는 6개여야합니다.");
    }

    @DisplayName("당첨넘버_문자예외_테스트")
    @Test
    void 당첨넘버_문자예외_테스트() {
        assertThatThrownBy(() -> WinningNumberParser.parseWinningNumbers("1, 2, 3, 4, 5, a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 문자가 숫자가 아닙니다.");
    }
}