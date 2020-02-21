package lotto;

import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또숫자가_범위를_벗어날_경우_예외_발생(int number) {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 1~45사이어야 합니다.");
    }
}
