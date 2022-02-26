package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.number.BonusNumber;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {

    @Test
    void 보너스_번호_생성_테스트() {
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThat(bonusNumber.getLottoNumber()).isEqualTo(1);
    }

    @Test
    void 보너스_번호_생성_테스트_범위1() {
        assertThatThrownBy(() ->
                new BonusNumber(-1))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 보너스_번호_생성_테스트_범위2() {
        assertThatThrownBy(() ->
                new BonusNumber(46))
                .isInstanceOf(RuntimeException.class);
    }
}
