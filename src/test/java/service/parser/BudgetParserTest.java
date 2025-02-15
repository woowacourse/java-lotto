package service.parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BudgetParserTest {
    @DisplayName("금액입력_문자예외_테스트")
    @Test
    void 금액입력_문자예외_테스트() {
        assertThatThrownBy(() -> BudgetParser.parseLottoCount("14000a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.NOT_NUMBER.getMessage());
    }

    @DisplayName("금액입력_1000원단위예외_테스트")
    @Test
    void 금액입력_1000원단위예외_테스트() {
        assertThatThrownBy(() -> BudgetParser.parseLottoCount("14020"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.INVALID_MONEY_INPUT.getMessage());
    }
}