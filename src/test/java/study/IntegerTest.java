package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntegerTest {
    @Test
    @DisplayName("음수를 파싱할 때 어떻게 되는 지 학습")
    void parseNegativeNumber() {
        assertThat(Integer.parseInt("-1")).isEqualTo(-1);

    }

    @Test
    @DisplayName("글자를 파싱할 때 어떻게 되는 지 학습")
    void parseString() {
        String input = "가";
        assertThatThrownBy(() -> Integer.parseInt(input))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage(String.format("For input string: \"%s\"", input));
    }
}
