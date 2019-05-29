package lottogame.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersParserTest {
    @Test
    void String입력을_정수List로_반환하는지_테스트() {
        String input = "1,2,3,4,5,6";
        List<Integer> accept = Arrays.asList(1,2,3,4,5,6);

        assertThat(LottoNumbersParser.parse(input)).isEqualTo(accept);
    }

    @Test
    void 정수를_입력하지_않았을_경우_예외_처리() {
        String input = "a,2,3,4,5,6";
        assertThrows(NumberFormatException.class,()->LottoNumbersParser.parse(input));
    }
}
