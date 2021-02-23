package lotto.domain;

import lotto.exception.IllegalLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "-1"})
    @DisplayName("숫자인지 검사")
    void validateNumbers(String input) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.createLottoNumber(input);
        }).isInstanceOf(IllegalLottoNumberException.class)
        .hasMessage(input + " : 올바른 형식이 아닙니다.");
    }

    @DisplayName("1~49 사이의 숫자인지 검사")
    @Test
    void validateNumbersByRange() {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.createLottoNumber("55");
        }).isInstanceOf(IllegalLottoNumberException.class)
        .hasMessage("55 : 로또번호의 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("동일성 검사")
    void checkEqual() {
        LottoNumber lottoNumber1 = LottoNumber.createLottoNumber("1");
        LottoNumber lottoNumber2 = LottoNumber.createLottoNumber("1");
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
