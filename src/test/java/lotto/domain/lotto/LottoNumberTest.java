package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @Test
    void generate_valid() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void generate_invalid(int input) {
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @DisplayName("앞뒤 공백 제거 후 생성")
    @CsvSource(value = {"1,1", " 1,1", "1 ,1", " 1 ,1", "   1   ,1"})
    void valueOf_success(String input, int expected) {
        assertThat(LottoNumber.valueOf(input)).isEqualTo(new LottoNumber(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0.1", "word"})
    void generate_valid_not_int(String input) {
        assertThatThrownBy(() -> LottoNumber.valueOf(input))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "45,45"})
    void toInt(int input, int expected) {
        LottoNumber lottoNumber = new LottoNumber(input);

        assertThat(lottoNumber.toInteger()).isEqualTo(expected);
    }
}