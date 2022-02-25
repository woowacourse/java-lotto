package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 47})
    void 로또_생성_번호_범위_에러_처리(int input) {
        assertThatThrownBy(() -> LottoNumber.valueOf(input))
            .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void 로또_정상_생성_처리(int input) {
        assertThat(LottoNumber.valueOf(input).get()).isEqualTo(input);
    }
}