package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    LottoNumber lottoNumber;

    @Test
    void LottoNumber_생성_확인() {
        lottoNumber = new LottoNumber(33);
        assertThat(lottoNumber.getNumber()).isEqualTo(33);
    }

    @Test
    void LottoNumber_번호_범위_45_초과_생성_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            lottoNumber = new LottoNumber(46);
        });
    }

    @Test
    void LottoNumber_번호_범위_1_미만_생성_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            lottoNumber = new LottoNumber(0);
        });
    }
}
