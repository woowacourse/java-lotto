package lotto.domain.primitive;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.primitive.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 99})
    @DisplayName("범위 초과 로또 번호")
    void generateOutRangeLottoNumber(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("범위가 초과");
    }
}
