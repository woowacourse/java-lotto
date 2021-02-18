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
    @ValueSource(strings = {"a", "-1", "55"})
    @DisplayName("1~49의 숫자인지 검사")
    void validateNumbers(String input) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(input);
        }).isInstanceOf(IllegalLottoNumberException.class);
    }

    @Test
    @DisplayName("동일성 검사")
    void checkEqual() {
        LottoNumber lottoNumber1 = new LottoNumber("1");
        LottoNumber lottoNumber2 = new LottoNumber("1");
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
