package lotto;

import lotto.domain.lotto.IllegalLottoNumberException;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void 숫자_생성_테스트() {
        LottoNumber lottoNumber = new LottoNumber(3);
        assertThat(lottoNumber).isEqualTo(new LottoNumber(3));
    }

    @Test
    void 숫자_범위_넘을때_테스트() {
        assertThrows(IllegalLottoNumberException.class, () -> new LottoNumber(46));
    }


}