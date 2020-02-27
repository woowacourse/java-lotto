package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerTest {
    @Test
    @DisplayName("음수를 파싱할 때 어떻게 되는 지 학습")
    void parseNegativeNumber() {
        assertThat(Integer.parseInt("-1")).isEqualTo(-1);

    }
}
