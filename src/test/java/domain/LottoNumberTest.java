package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ValueSource(ints = {0, 46, -1})
    @ParameterizedTest
    void 로또_숫자가_범위에서_벗어나면_예외를_던진다(int value) {
        assertThatThrownBy(() -> LottoNumber.of(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}