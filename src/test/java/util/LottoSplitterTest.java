package util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoSplitterTest {

    @Test
    void split_() {
        String input = "1,2";

        assertThat(LottoSplitter.split(input)).isEqualTo(Arrays.asList("1", "2"));
    }

    @Test
    void split_구분자_전후_공백() {
        String input = "1, 2 ,3";

        assertThat(LottoSplitter.split(input)).isEqualTo(Arrays.asList("1", "2", "3"));
    }

    @Test
    void split_구분자없는_입력() {
        String input = "1* 2* 3";

        assertThat(LottoSplitter.split(input)).isEqualTo(Arrays.asList("1* 2* 3"));
    }
}