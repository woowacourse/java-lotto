package lotto.domain.lotto;

import lotto.domain.LottoNumber;
import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_MAX = 45;

    @Test
    void Lotto_번호가_잘들어가는지_테스트() {
        LottoNumber lottoNumber = new LottoNumber(10);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(10));
    }

    @Test
    void Lotto_번호가_1미만인_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(NUMBER_MIN - 1);
        });
    }

    @Test
    void Lotto_번호가_45초과인_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(NUMBER_MAX + 1);
        });
    }
}
