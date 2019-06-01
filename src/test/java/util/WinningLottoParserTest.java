package util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoParserTest {

    @Test
    void parse_숫자와_컴마가_아닌입력_실패() {
        String input = "a, b, c, e, d, f";
        assertThrows(NumberFormatException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_컴마사이에_공백있는_숫자() {
        String input = " 1 1, 2, 3, 4, 5, 6";

        assertThrows(NumberFormatException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_가징작은_가능한숫자보다_적은숫자포함() {
        String input = "0, 2, 3, 4, 5, 6";

        assertThrows(IllegalArgumentException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_가장큰_가능한숫자보다_큰숫자포함() {
        String input = "40, 41, 42, 43, 44, 46";

        assertThrows(IllegalArgumentException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_로또에_필요한_숫자개수_부족() {
        String input = "1, 2, 3, 4, 5";

        assertThrows(IllegalArgumentException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_로또에_필요한_숫자개수_초과() {
        String input = "1, 2, 3, 4, 5, 6, 7";

        assertThrows(IllegalArgumentException.class, () -> WinningLottoParser.parse(input));
    }

    @Test
    void parse_중복있는_숫자() {
        String input = "1, 1, 3, 4, 5, 6";

        assertThrows(IllegalArgumentException.class, () -> WinningLottoParser.parse(input));
    }

    // Todo:
    // NumberFormatException 이 IllegalArgumentException.class 을 상속받음...
    // 따라서.... 올바르게 잡아내질 못하고 있음 ( catch(Exception e) 같은 느낌??)
}