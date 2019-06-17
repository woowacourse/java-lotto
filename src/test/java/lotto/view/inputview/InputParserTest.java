package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    private List<Integer> actual = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    void Price_입력이_없을_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.getPurchasePrice("");
        });
    }

    @Test
    void Price_null_입력() {
        assertThrows(NullPointerException.class, () -> {
            InputParser.getPurchasePrice(null);
        });
    }

    @Test
    void Price_숫자가_아닐_경우() {
        assertThrows(NumberFormatException.class, () -> {
            InputParser.getPurchasePrice("a");
        });
    }

    @Test
    void Price_금액_마지막_원_허용() {
        Integer answer = InputParser.getPurchasePrice("1000");
        assertThat(answer).isEqualTo(InputParser.getPurchasePrice("1000원"));
    }

    @Test
    void Lotto_Num_입력이_없을_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.getLottoNum("");
        });
    }

    @Test
    void Lotto_Num_null입력() {
        assertThrows(NullPointerException.class, () -> {
            InputParser.getLottoNum(null);
        });
    }

    @Test
    void Lotto_Num_slicing() {
        String input = "1,2,3,4,5,6";
        assertThat(actual).isEqualTo(InputParser.getLottoNum(input));
    }

    @Test
    void Lotto_Num_공백() {
        String input = "1, 2, 3 , 4, 5 ,  6 ";
        assertThat(actual).isEqualTo(InputParser.getLottoNum(input));
    }
}