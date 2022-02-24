package lotto.model;

import static lotto.model.LottoNumber.INVALID_LOTTO_NUMBER_RANGE;
import static lotto.model.LottoNumber.NOT_NUMBER_OF_LOTTO;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"숫자아님", "aa", "#@"})
    @DisplayName("로또 번호는 숫자여야 한다.")
    void notNumber(String input) {
        // then
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_NUMBER_OF_LOTTO);
    }

    @ParameterizedTest
    @DisplayName("로또 번호는 1부터 45까지의 범위가 아니면 예외를 던진다.")
    @ValueSource(strings = {"0", "46"})
    void notInRange(String input) {
        // then
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_RANGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    @DisplayName("로또 번호는 1부터 45까지의 범위에 속한다면 예외를 던지지 않고 생성된다")
    void inRange(String input) {
        // then
        assertThat(new LottoNumber(input)).isNotNull();
    }
}
