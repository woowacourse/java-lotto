package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생")
    void incorrect(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 25, 45})
    @DisplayName("숫자가 범위 내에서 생성됨")
    void correct(int number) {
        assertThatNoException().isThrownBy(() -> LottoNumber.of(number));
    }
}
