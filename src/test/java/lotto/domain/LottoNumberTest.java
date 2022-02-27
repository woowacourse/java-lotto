package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "-1"})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생")
    void incorrect(int invalidLottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidLottoNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "45"})
    @DisplayName("숫자가 범위 내에서 생성됨 테스트 통과")
    void correct(int validLottoNumber) {
        assertThatNoException().isThrownBy(() -> new LottoNumber(validLottoNumber));
    }
}
