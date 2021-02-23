package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 99})
    @DisplayName("범위 초과 로또 번호")
    void generateOutRangeLottoNumber(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LottoNumber.OUT_RANGE_NUMBER_ERROR_MESSAGE);
    }
}
