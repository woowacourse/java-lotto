package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import lotto.domain.vo.LottoPurchaseMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("구입 금액을 기반으로 로또 티켓을 자동 발급한다.")
    @Test
    void checkNormalCase() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when & then
        assertThatCode(() -> lottoMachine.purchase(LottoPurchaseMoney.create(14000), new ArrayList<>(0)))
                .doesNotThrowAnyException();
    }
}