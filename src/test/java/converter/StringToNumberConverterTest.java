package converter;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringToNumberConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {"s", "1s"})
    void 입력값이_숫자가_아닌_경우_예외를_반환한다(String input) {
        // given
        StringToLottoNumberConverter converter = new StringToLottoNumberConverter();

        // when & then
        assertThatThrownBy(() -> converter.convert(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }
}
