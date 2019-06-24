package lotto.model.object;

import lotto.model.BonusBall;
import lotto.model.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class BonusBallTest {

        @Test
        void 로또_번호_일치_검사() {
                BonusBall bonusBall = new BonusBall(5);
                assertThat(bonusBall.IsSame(LottoNumber.getInstance(5))).isEqualTo(true);
        }
}