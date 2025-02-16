package view.parser;

import static domain.excepetion.ExceptionMessage.INVALID_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void 로또_번호_구분자_테스트() {
        assertThatThrownBy(() -> {
            InputParser.parseWinningLotto("1/2/3/4/5/6");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_FORMAT);
    }

    @Test
    void 로또_번호_정수_테스트() {
        assertThatThrownBy(() -> {
            InputParser.parseWinningLotto("1,2,3,4,5,a");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_FORMAT);
    }
}