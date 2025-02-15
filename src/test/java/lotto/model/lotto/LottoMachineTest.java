package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Money;

class LottoMachineTest {

    @DisplayName("구매 금액에 맞게 로또 티켓을 자동 발행해 준다.")
    @Test
    void issueAutomaticLottoWithBuyingAmount() {
        LottoMachine lottoMachine = new LottoMachine();
        Money buyingAmount = new Money(1_000);

        Lottos issuedLottos = lottoMachine.issueAutomatic(buyingAmount, () -> List.of(1, 2, 3, 4, 5, 6));

        assertThat(issuedLottos.getLottos()).hasSize(1);
    }

}
