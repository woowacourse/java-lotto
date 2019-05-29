package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 범위_내의_숫자로_이루어진_Lotto_Number객체_생성() {
        assertThat(LottoNumber.Of(10)).isEqualTo(LottoNumber.Of(10));
    }

    @Test
    void 범위_밖의_로또_번호를_요청하면_에러_발생() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.Of(0));
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.Of(46));
    }
}