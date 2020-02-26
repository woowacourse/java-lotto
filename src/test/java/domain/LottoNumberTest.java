package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("LottoNumber 생성자 유효성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void LottoNumberConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoNumber 가 bonusNumber 일 때 생성자 유효성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "", " ", "hello"})
    void LottoNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoNumber 의 getLottoNumber 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "43,43"})
    void getLottoNumberTest(int input, String expected) {
        Assertions.assertThat(LottoNumber.getLottoNumber(input).toString()).isEqualTo(expected);
    }
}
