package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @DisplayName("하나의 로또 숫자를 생성할 수 있다.")
    @Test
    void ok() {
        Assertions.assertDoesNotThrow(() -> new LottoNumber(30));
    }

    @DisplayName("숫자가 범위 밖일 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNumberNotInRange() {
        assertThatThrownBy(() -> new LottoNumber(80))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 수여야 합니다.");
    }
}
