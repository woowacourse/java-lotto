package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertInputTest {
    @Test
    @DisplayName(",기준으로 분리")
    void splitLottoNumbersTest() {
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(ConvertInput.splitLottoNumbers("1,2,3,4,5,6")).isEqualTo(result);
    }

    @Test
    @DisplayName("공백 제거 검사")
    void removeBlankTest() {
        assertThat(ConvertInput.removeBlank("1, 2, 3, 4, 5, 6")).isEqualTo("1,2,3,4,5,6");
    }
}
