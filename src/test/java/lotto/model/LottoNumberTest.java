package lotto.model;

import static lotto.constant.LottoNumberConstants.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("하나의 로또 숫자를 생성할 수 있다.")
    @Test
    void ok() {
        Assertions.assertDoesNotThrow(() -> new LottoNumber(30));
    }

    @DisplayName("숫자가 범위 밖일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void shouldThrowException_WhenNumberNotInRange(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 번호는 %d부터 %d 사이의 수여야 합니다.",
                        LOTTO_NUMBER_MIN.value(), LOTTO_NUMBER_MAX.value())
                );
    }
}
