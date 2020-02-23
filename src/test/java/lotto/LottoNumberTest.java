package lotto;

import lotto.domain.LottoNumber;
import lotto.exception.LottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @DisplayName("로또숫자가 범위 안일 경우 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void inRange(int number) {
        assertThat(new LottoNumber(number).toString()).isEqualTo(String.valueOf(number));
    }

    @DisplayName("로또숫자가 범위를 벗어날 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void outOfRange(int number) {
        assertThatThrownBy(() -> {
            new LottoNumber(number);
        }).isInstanceOf(LottoNumberException.class)
                .hasMessage("로또 숫자는 1~45사이어야 합니다.");
    }
}
