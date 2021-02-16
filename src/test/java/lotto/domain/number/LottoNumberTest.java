package lotto.domain.number;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void inputNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.equals(1)).isTrue();

        lottoNumber = new LottoNumber("45");
        assertThat(lottoNumber.equals(45)).isTrue();
    }
}
