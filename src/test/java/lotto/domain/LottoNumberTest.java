package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1에서 45 사이의 문자열 값이 아닌 값을 입력했을 경우")
    void incorrect_string_input_range_1_to_45(int value) {
        assertThatThrownBy(() -> {
            new LottoNumber(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("로또 숫자가 같을 때 같다라고 보는 경우")
    void equals_same(int value) {
        LottoNumber lottoNumber1 = new LottoNumber(value);
        LottoNumber lottoNumber2 = new LottoNumber(value);
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    @DisplayName("로또 숫자 객체를 널과 비교하는 경우")
    void equals_null() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = null;
        assertThat(lottoNumber1).isNotEqualTo(lottoNumber2);
    }
}
