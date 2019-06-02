package util;

import domain.LottoGenerator;
import domain.Number;
import domain.WinningLotto;
import exception.LottoDuplicationException;
import exception.LottoExceedSizeException;
import exception.NumberOutOfRangeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoParserTest {

    @Test
    void parse_숫자와_컴마가_아닌입력_실패() {
        String input = "a, b, c, e, d, f";

        assertThrows(NumberFormatException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_컴마사이에_공백있는_숫자() {
        String input = " 1 1, 2, 3, 4, 5, 6";

        assertThrows(NumberFormatException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_컴마아닌구분자사용() {
        String input = " 1* 2, 3, 4, 5, 6";

        assertThrows(NumberFormatException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_가징작은_가능한숫자보다_적은숫자포함() {
        String input = "0, 2, 3, 4, 5, 6";

        assertThrows(NumberOutOfRangeException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_가장큰_가능한숫자보다_큰숫자포함() {
        String input = "40, 41, 42, 43, 44, 46";

        assertThrows(NumberOutOfRangeException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_로또에_필요한_숫자개수_부족() {
        String input = "1, 2, 3, 4, 5";

        assertThrows(LottoExceedSizeException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_로또에_필요한_숫자개수_초과() {
        String input = "1, 2, 3, 4, 5, 6, 7";

        assertThrows(LottoExceedSizeException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_중복있는_숫자() {
        String input = "1, 1, 3, 4, 5, 6";

        assertThrows(LottoDuplicationException.class, () -> LottoParser.parse(input));
    }

    @Test
    void parse_정렬된_올바른입력() {
        String input = "1, 2, 3, 4, 5, 6";

        assertThat(LottoParser.parse(input)).isEqualTo(LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void parse_정렬안된_올바른입력() {
        String input = "45, 44, 43, 42, 41, 40";

        assertThat(LottoParser.parse(input)).isEqualTo(LottoGenerator.from(Arrays.asList(40, 41, 42, 43, 44, 45)));
    }
}