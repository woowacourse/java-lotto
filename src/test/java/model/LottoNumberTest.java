package model;

import static model.LottoNumber.LOTTO_NUMBER_POOL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("로또 번호는 1부터 45까지의 범위가 아니면 예외를 던진다.")
    @ValueSource(ints = {0, 46})
    void notInRange(int input) {
        // then
        assertThatThrownBy(() -> LottoNumber.valueOf(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호는 1부터 45까지의 범위에 속한다면 예외를 던지지 않고 생성된다")
    void inRange(int input) {
        // then
        assertThat(LottoNumber.valueOf(input)).isEqualTo(LOTTO_NUMBER_POOL.get(input));
    }
}
