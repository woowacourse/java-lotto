package lotto.domain;

import static lotto.common.ErrorMessage.ONLY_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Test
    void 같은_숫자면_True를_반환한다() {
        LottoNumber number1 = LottoNumber.from(5);
        LottoNumber number2 = LottoNumber.from(5);

        assertThat(number1).isEqualTo(number2);
    }

    @Test
    void 숫자는_1과_45_사이만_가능하다() {
        IntStream.range(1, 45)
                .forEach(i -> assertThatCode(() -> new LottoNumber(i))
                        .doesNotThrowAnyException());

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 음수와_경계값은_허용하지_않는다(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_LOTTO_NUMBER.getMessage());
    }

    @Test
    void 숫자_비교가_가능하다() {
        LottoNumber number1 = LottoNumber.from(4);
        LottoNumber number2 = LottoNumber.from(5);

        assertThat(number1).isLessThan(number2);
        assertThat(number2).isGreaterThan(number1);
    }
}
