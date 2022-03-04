package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("볼 번호의 범위가 1~45가 아닌 경우 예외 처리")
    @ValueSource(ints = {-1, 0, 46})
    void validateRangeBonusNumberTest(int bonusNumber) {
        assertThatThrownBy(() ->
            new LottoNumber(bonusNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("볼 번호가 1~45 범위 내에 해당하지 않습니다.");
    }

    @Test
    @DisplayName("같은 번호의 볼에 관한 동등성 테스트")
    void equalsTest() {
        LottoNumber actual = new LottoNumber(1);
        LottoNumber expected = new LottoNumber(1);

        assertThat(actual).isEqualTo(expected);
    }
}
