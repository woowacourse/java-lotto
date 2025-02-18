package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Money;

class LottoMachineTest {

    @DisplayName("구매 금액이 로또 가격보다 작으면 로또 기계를 생성할 수 없다.")
    @Test
    void createLottoMachineWithBuyingAmountLessThanLottoPrice() {
        assertThatThrownBy(() -> new LottoMachine(new Money(999)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액에 맞게 로또 티켓을 자동 발행해 준다.")
    @Test
    void issueAutomaticLottoWithBuyingAmount() {
        Money buyingAmount = new Money(1_000);
        LottoMachine lottoMachine = new LottoMachine(buyingAmount);

        List<Lotto> issuedLottos = lottoMachine.issueAutomatic(() -> List.of(1, 2, 3, 4, 5, 6));

        assertThat(issuedLottos).hasSize(1);
    }

    @DisplayName("구매 금액에 맞는 잔돈을 계산해 준다.")
    @Test
    void calculateChangeWithMoney() {
        Money buyingAmount = new Money(1_100);
        LottoMachine lottoMachine = new LottoMachine(buyingAmount);

        assertThat(lottoMachine.calculateChange()).isEqualTo(100);
    }

}
