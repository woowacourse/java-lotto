package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 로또_번호를_정상적으로_생성한다(final int value) {
        Assertions.assertThatCode(() -> LottoNumber.from(value))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 번호가_1과_45_사이가_아니면_예외를_발생한다(final int number) {
        Assertions.assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1과 45 사이여야 합니다.");
    }
}
