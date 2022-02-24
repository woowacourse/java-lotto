package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberTest {
    @Test
    void 로또_번호_일치_여부_검사() {
        LottoNumber lottoNumber = LottoNumber.valueOf(1);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(1);

        assertThat(lottoNumber).isEqualTo(lottoNumber2);
    }

    @Test
    void 로또_번호_불일치_여부_검사() {
        LottoNumber lottoNumber = LottoNumber.valueOf(1);
        LottoNumber lottoNumber2 = LottoNumber.valueOf(3);

        assertThat(lottoNumber).isNotEqualTo(lottoNumber2);
    }
}