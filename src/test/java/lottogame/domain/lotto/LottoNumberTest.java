package lottogame.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoNumberTest {
    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void of1() {
        LottoNumber lottoNumber = LottoNumber.of(10);
        assertEquals(lottoNumber, LottoNumber.of(10));
    }

    @ParameterizedTest
    @DisplayName("1~45사이의 숫자가 아니면 예외 발생하는지 확인")
    @ValueSource(ints = {-1, 0, 46})
    void of2(int input) {
        assertThatThrownBy(() -> LottoNumber.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1과 45사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("1~45사이의 숫자를 나타내는 문자가 아니면 예외 발생하는지 확인")
    @ValueSource(strings = {"-1", "0", "46", "1a", "2가"})
    void of3(String input) {
        assertThatThrownBy(() -> LottoNumber.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1과 45사이의 숫자여야 합니다.");
    }
}
