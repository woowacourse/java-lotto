package domain;

import static exception.ExceptionMessage.LOTTO_RANGE_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 범위 테스트 - 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void lottoNumberRangeException(int number) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_RANGE_ERROR.getMessage());
    }
}