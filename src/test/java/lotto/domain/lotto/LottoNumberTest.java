package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("올바른 입력")
    @ValueSource(ints = {1, 45})
    void generate_valid() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("수가 범위를 벗어남")
    @ValueSource(ints = {0, 46})
    void generate_invalid(int input) {
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("앞뒤 공백 제거 후 생성")
    @CsvSource(value = {"1,1", " 1,1", "1 ,1", " 1 ,1", "   1   ,1"})
    void valueOf_success(String input, int expected) {
        assertThat(LottoNumber.valueOf(input)).isEqualTo(new LottoNumber(expected));
    }

    @ParameterizedTest
    @DisplayName("0~45사이의 정수가 아닌 입력")
    @ValueSource(strings = {"-1", "0.1", "word", "1 1"})
    void generate_valid_not_int(String input) {
        assertThatThrownBy(() -> LottoNumber.valueOf(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("값 객체 비교")
    @CsvSource(value = {"1,1", "45,45"})
    void equals(int input) {
        LottoNumber lottoNumber1 = new LottoNumber(input);
        LottoNumber lottoNumber2 = new LottoNumber(input);

        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
        assertThat(lottoNumber1).hasSameHashCodeAs(lottoNumber2);
    }

    @ParameterizedTest
    @DisplayName("입력한 값대로 수 반환")
    @CsvSource(value = {"1", "45"})
    void toInt(int input) {
        LottoNumber lottoNumber = new LottoNumber(input);

        assertThat(lottoNumber.toInteger()).isEqualTo(input);
    }
}