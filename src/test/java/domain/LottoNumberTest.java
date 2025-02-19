package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import error.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 40, 45})
    void _1부터_45사이의_정수는_유효한_로또번호이다(int validNumber) {
        assertThatCode(() -> new LottoNumber(validNumber))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 범위를_벗어난_숫자로_로또번호를_생성하면_예외가_발생한다(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
    }

}
