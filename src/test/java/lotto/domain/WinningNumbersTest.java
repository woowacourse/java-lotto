package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    @DisplayName("중복된 숫자로 생성시 예외 발생")
    void incorrect() {
        assertThatThrownBy(() -> new WinningNumbers("2,2,3,4,5,6")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 숫자가 없는 경우")
    void correct() {
        assertThatNoException().isThrownBy(() -> new WinningNumbers("1,2,3,4,5,6"));
    }

    @Test
    @DisplayName("문자열이 포함된 경우")
    void incorrect2() {
        assertThatThrownBy(() -> new WinningNumbers("1,aa,3,4,5,6")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백이 포함된 경우")
    void correct2() {
        assertThatNoException().isThrownBy(() -> new WinningNumbers("1,  8  ,3,4 ,5 ,6"));
    }
}
