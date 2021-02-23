package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "-1", ""})
    @DisplayName("로또 숫자 포맷 검증")
    void validateNumbers(String input) {
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 범위 초과했을 경우")
    void validateNumbersRange() {
        String input = "55";
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일성 검사")
    void checkEqual() {
        LottoNumber lottoNumber1 = new LottoNumber("1");
        LottoNumber lottoNumber2 = new LottoNumber("1");
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
