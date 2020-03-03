package lotto.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {
    @Test
    @DisplayName("string 을 (,)구분자로 파싱하는 기능")
    void parse_string_input() {
        String input = "1,2,3,4,5,6";
        String[] expected = {"1","2","3","4","5","6"};

        Assertions.assertThat(StringUtils.parseString(input)).isEqualTo(expected);
    }
}