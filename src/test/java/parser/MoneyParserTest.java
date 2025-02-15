package parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.parser.MoneyParser;

public class MoneyParserTest {
    @DisplayName("금액입력_문자예외_테스트")
    @Test
    void 금액입력_문자예외_테스트() {
        assertThatThrownBy(() -> MoneyParser.parseLottoCount("14000a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 문자가 숫자가 아닙니다.");
    }

    @DisplayName("금액입력_1000원단위예외_테스트")
    @Test
    void 금액입력_1000원단위예외_테스트() {
        assertThatThrownBy(() -> MoneyParser.parseLottoCount("14020"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천원 단위로 입력하세요.");
    }
}