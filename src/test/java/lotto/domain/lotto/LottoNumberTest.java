package lotto.domain.lotto;

import lotto.domain.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void Lotto_번호가_잘들어가는지_테스트() {
        LottoNumber lottoNumber = new LottoNumber(10);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(10));
    }

    @Test
    void Lotto_번호가_1미만인_경우() {
        assertThrows(InvalidLottoNumberException.class, ()->{
            new LottoNumber(0);
        });
    }

    @Test
    void Lotto_번호가_45초과인_경우() {
        assertThrows(InvalidLottoNumberException.class, ()->{
            new LottoNumber(46);
        });
    }
}
