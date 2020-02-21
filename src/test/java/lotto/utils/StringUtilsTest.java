package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void 문자열_기준에_따라_분리() {
        //given
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        //when
        List<Integer> parsedInput = StringUtils.parseWithStandard(input);
        //then
        assertThat(parsedInput).isEqualTo(expected);
    }

}
