package parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.parser.MoneyParser;

public class MoneyParserTest {
    @DisplayName("금액입력_문자예외_테스트")
    @Test
    void 금액입력_문자예외_테스트() {
        assertThatThrownBy(() -> MoneyParser.parseLottoCount("14000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.NOT_NUMBER.getMessage());
    }

    @DisplayName("금액입력_1000원단위예외_테스트")
    @Test
    void 금액입력_1000원단위예외_테스트() {
        assertThatThrownBy(() -> MoneyParser.parseLottoCount("14020"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.INVALID_MONEY_INPUT.getMessage());
    }
}