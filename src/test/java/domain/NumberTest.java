package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {

    @ParameterizedTest
    @DisplayName("1번 ~ 45번까지의 범위를 벗어난 경우 예외를 발생시킨다")
    @ValueSource(ints = {-1, 0, 46})
    void checkNumberRange(int number) {
        assertThatThrownBy(() -> new Number(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("번호는 1 ~ 45의 숫자여야 합니다");
    }
}
