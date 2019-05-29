package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningNumbersParserTest {
    List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    void 입력이_없을_경우() {
        assertThrows(NullPointerException.class, () -> {
            WinningNumbersParser.getWinningNumbers("");
        });
    }

    @Test
    void null_입력() {
        assertThrows(NullPointerException.class, () -> {
            WinningNumbersParser.getWinningNumbers(null);
        });
    }

    @Test
    void 쉼표로_구분() {
        assertThat(actual).isEqualTo(WinningNumbersParser.getWinningNumbers("1,2,3,4,5,6"));
    }

    @Test
    void 정수가_아닌_번호_존재() {
        assertThrows(NumberFormatException.class, () -> {
            WinningNumbersParser.getWinningNumbers("1,2,3,4,5,a");
        });
    }

    @Test
    void 유효한_범위_번호() {
    }

    @Test
    void 번호_개수() {

    }

    @Test
    void 중복된_번호() {
    }
}