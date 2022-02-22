package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("1 ~ 45 숫자가 아닌 경우 예외 발생")
    @ValueSource(ints = {-1, 0, 46})
    @ParameterizedTest
    void numberRangeException(int number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoNumber(number));
    }
}
