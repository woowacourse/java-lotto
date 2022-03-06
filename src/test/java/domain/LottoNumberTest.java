package domain;

import exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {

    @DisplayName("범위 내 숫자를 입력했을 시 성공")
    @Test
    void range_success() {
        assertThatCode(() -> LottoNumber.valueOf(45))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위 내 숫자가 아니면 예외 발생")
    @ValueSource(ints = {0, 46})
    @ParameterizedTest
    void range_fail(int lottoNumber) {
        assertThatThrownBy(() -> LottoNumber.valueOf(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @DisplayName("LottoNumber는 오름차순 정렬")
    @Test
    void comparable() {
        assertThat(LottoNumber.valueOf(1)).isLessThan(LottoNumber.valueOf(45));
    }
}
