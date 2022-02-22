package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @DisplayName("유효한 로또 번호를 발급을 확인한다.")
    @Test
    void input_lottoNumbers_valid() {
        assertDoesNotThrow(() -> new LottoNumbers("1, 2, 3, 4, 5, 6"));
    }

    @DisplayName("중복된 로또 번호를 발급할시 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_duplicated() {
        assertThatThrownBy(() -> new LottoNumbers("1, 1, 3, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지난 주 당첨 번호 입력시 숫자가 아닌 경우 예외를 발생시킨다.")
    @Test
    void input_lottoNumbers_() {
        assertThatThrownBy(() -> new LottoNumbers("1, a, 3, 4, 5, 6"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
