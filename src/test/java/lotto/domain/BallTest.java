package lotto.domain;

import lotto.util.Creatable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallTest {

    @Test
    @DisplayName("Null값을 입력했을 경우")
    void inputNull() {
        assertThatThrownBy(() -> {
            Ball ball = new Ball(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈값을 입력했을 경우")
    void inputEmpty() {
        assertThatThrownBy(() -> {
            Ball ball = new Ball("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자를 입력했을 경우")
    void inputNotNumber() {
        assertThatThrownBy(() -> {
            Ball ball = new Ball("ball");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    @DisplayName("1에서 45 사이의 문자열 값이 아닌 값을 입력했을 경우")
    void incorrectStringInputRange1To45(String input) {
        assertThatThrownBy(() -> {
            Ball ball = new Ball(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
