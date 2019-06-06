package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumParserTest {
    List<Integer> actual = Arrays.asList(1,2,3,4,5,6);

    @Test
    void 입력이_없을_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumParser.getLottoNum("");
        });
    }

    @Test
    void null_입력() {
        assertThrows(NullPointerException.class, () -> {
            LottoNumParser.getLottoNum(null);
        });
    }

    @Test
    void slicing() {
        String input = "1,2,3,4,5,6";
        assertThat(actual).isEqualTo(LottoNumParser.getLottoNum(input));
    }

    @Test
    void 공백() {
        String input = "1, 2, 3 , 4, 5 ,  6 ";
        assertThat(actual).isEqualTo(LottoNumParser.getLottoNum(input));
    }
}