package util;

import exception.NumberOutOfRangeException;
import org.junit.jupiter.api.Test;
import domain.Number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberParserTest {

    @Test
    void parse_NUMBER_FROM() {
        String input = String.format("%d", Number.NUMBER_FROM);
        Number from = Number.from(Number.NUMBER_FROM);

        assertThat(NumberParser.parse(input)).isEqualTo(from);
    }

    @Test
    void parse_NUMBER_TO() {
        String input = String.format("%d", Number.NUMBER_TO);
        Number to = Number.from(Number.NUMBER_TO);

        assertThat(NumberParser.parse(input)).isEqualTo(to);
    }

    @Test
    void parse_NUMBER_범위초과() {
        String input = String.format("%d", Number.NUMBER_TO + 1);

        assertThrows(NumberOutOfRangeException.class, () -> NumberParser.parse(input));
    }

    @Test
    void parse_숫자와_컴마가_아닌입력_실패() {
        String input = "ab";

        assertThrows(NumberFormatException.class, () -> NumberParser.parse(input));
    }

    @Test
    void parse_사이에_공백있는_숫자() {
        String input = "1  2";

        assertThrows(NumberFormatException.class, () -> NumberParser.parse(input));
    }

    @Test
    void parse_컴마아닌구분자사용() {
        String input = "1* 2";

        assertThrows(NumberFormatException.class, () -> NumberParser.parse(input));
    }
}