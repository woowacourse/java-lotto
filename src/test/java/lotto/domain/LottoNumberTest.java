package lotto.domain;

import lotto.domain.exception.LottoNumberOfOverRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 로또_숫자_생성하는_테스트() {
        LottoNumber lottoNumber = new LottoNumber(45);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(45));
    }

    @Test
    void 로또번호가_45_보다_큰_경우_테스트() {
        assertThrows(LottoNumberOfOverRangeException.class, () -> {
            new LottoNumber(46);
        });
    }

    @Test
    void 로또번호가_1_보다_작은_경우_테스트() {
        assertThrows(LottoNumberOfOverRangeException.class, () -> {
            new LottoNumber(0);
        });
    }
}
