package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberTest {
    @Test
    void 로또_번호_일치_여부_검사() {
        LottoNumber lottoNumber = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);

        assertThat(lottoNumber).isEqualTo(lottoNumber2);
    }
}