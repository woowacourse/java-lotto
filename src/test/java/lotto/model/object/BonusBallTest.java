package lotto.model.object;

<<<<<<< HEAD
import lotto.model.BonusBall;
import lotto.model.LottoNumber;
=======
>>>>>>> da7b5280162c03e3cb85956a36ace4e4eaa36719
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class BonusBallTest {

        @Test
        void 로또_번호_일치_검사() {
                BonusBall bonusBall = new BonusBall(5);
                assertThat(bonusBall.IsSame(LottoNumber.getInstance(5))).isEqualTo(true);
        }
}