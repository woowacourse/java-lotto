package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("1 ~ 45 숫자가 아닌 경우 예외 발생")
    @ValueSource(ints = {-1, 0, 46})
    @ParameterizedTest
    void numberRangeException(int inputNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoNumber(inputNumber))
                .withMessage("[ERROR] 입력값이 1 이상 45 이하여야 합니다.");
    }

    @Test
    void comparable() {
        final LottoNumber lowerLottoNumber = new LottoNumber(2);
        final LottoNumber largerLottoNumber = new LottoNumber(20);
        assertThat(lowerLottoNumber).isLessThan(largerLottoNumber);
    }

    @Test
    void equalsAndHashCode() {
        assertThat(new LottoNumber(1))
                .isEqualTo(new LottoNumber(1))
                .hasSameHashCodeAs(new LottoNumber(1));
    }
}
