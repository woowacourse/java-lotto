package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("Should_LottoNumber 유효성 통과_When_LottoNumber 를 정수형으로 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void LottoNumberConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            LottoNumber.from(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Should_LottoNumber 유효성 통과_When_LottoNumber 가 bonusNumber 일 경우(String으로 받았을 때) 생성")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "", " ", "hello"})
    void LottoNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            LottoNumber.from(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Should_LottoNumber 가 객체로 가져옴_When_LottoNumber 의 getter")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "43,43"})
    void getLottoNumberTest(int input, String expected) {
        Assertions.assertThat(LottoNumber.from(input).toString()).isEqualTo(expected);
    }
}
