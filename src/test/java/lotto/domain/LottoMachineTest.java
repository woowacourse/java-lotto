package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("구입 금액을 기반으로 로또 티켓을 자동 발급한다.")
    @Test
    void 로또_머신_정상_발급() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when & then
        assertThatCode(() -> lottoMachine.purchase("14000"))
                .doesNotThrowAnyException();
    }
}