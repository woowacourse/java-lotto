package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1에서 45 사이의 문자열 값이 아닌 값을 입력했을 경우")
    void incorrect_string_input_range_1_to_45(int value) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
