package lotto.model.object;

import lotto.model.creator.BonusBallCreator;
import lotto.model.creator.LottoNumberCreator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BonusBallTest {

        @Test
        void 로또_번호_일치_검사() {
                assertThat(BonusBallCreator.create(5).compareToLottoNumber(LottoNumberCreator.create(5))).isEqualTo(true);
        }
}