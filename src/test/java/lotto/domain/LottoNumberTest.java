package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void Lotto_번호가_잘들어가는지_테스트() {
        assertThat(new LottoNumber(10)).isEqualTo(new LottoNumber(10));
    }

    @Test
    void Lotto_번호가_1미만인_경우() {
        assertThrows(InvalidLottoNumber.class, ()->{
            new LottoNumber(0);
        });
    }

    @Test
    void Lotto_번호가_45초과인_경우() {
        assertThrows(InvalidLottoNumber.class, ()->{
            new LottoNumber(46);
        });
    }
}
