package model;

import static model.LottoNumber.INVALID_LOTTO_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("로또 번호는 1부터 45까지의 범위가 아니면 예외를 던진다.")
    @ValueSource(ints = {0, 46})
    void notInRange(int input) {
        // then
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_RANGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호는 1부터 45까지의 범위에 속한다면 예외를 던지지 않고 생성된다")
    void inRange(int input) {
        // then
        assertThat(new LottoNumber(input)).isNotNull();
    }
}
