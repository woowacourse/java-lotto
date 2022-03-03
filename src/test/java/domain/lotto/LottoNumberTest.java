package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.lotto.LottoNumRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberTest {
    @Test
    void 로또_번호_일치_여부_검사() {
        LottoNumber lottoNumber = LottoNumber.getInstance(1);
        LottoNumber lottoNumber2 = LottoNumber.getInstance(1);

        assertThat(lottoNumber).isEqualTo(lottoNumber2);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 47})
    void 로또_범위_범위_에러_처리(int input) {
        assertThatThrownBy(() -> LottoNumber.getInstance(input))
                .isInstanceOf(LottoNumRangeException.class)
                .hasMessage("로또 번호는 1에서 45사이의 수여야 합니다.");
    }
}