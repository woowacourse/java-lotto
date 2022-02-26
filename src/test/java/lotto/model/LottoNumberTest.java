package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.number.LottoNumber;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void 로또_숫자_생성_테스트() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.getLottoNumber()).isEqualTo(1);
    }

    @Test
    void 로또_숫자_생성_테스트_범위1() {
        assertThatThrownBy(() ->
                new LottoNumber(-1))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또_숫자_생성_테스트_범위2() {
        assertThatThrownBy(() ->
                new LottoNumber(46))
                .isInstanceOf(RuntimeException.class);
    }
}
