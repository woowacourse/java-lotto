package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberTest {
    @Test
    void 로또_번호_일치_여부_검사() {
        LottoNumber lottoNumber = LottoNumber.from(1);
        LottoNumber lottoNumber2 = LottoNumber.from(1);

        assertThat(lottoNumber).isEqualTo(lottoNumber2);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 47})
    void 로또_범위_범위_에러_처리(int input) {
        assertThatThrownBy(() -> LottoNumber.from(input))
                .isInstanceOf(Exception.class);
    }
}