package parser;

import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_POSITIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class InputParserTest {

    @Nested
    class 로또_번호_입력_테스트 {
        @Test
        void 구분자가_다르면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                    InputParser.lottoParser("1/2/3/4/5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

        @Test
        void 문자가_입력되면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                InputParser.lottoParser("1,2,3,4,5,A");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }
    }

}