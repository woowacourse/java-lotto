package lotto.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    @DisplayName("문자열 기준에 따라 분리")
    void parseWithDelimiter() {
        //given
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        //when
        List<Integer> parsedInput = StringUtils.parseWithDelimiter(input);
        //then
        assertThat(parsedInput).isEqualTo(expected);
    }

}
