package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AutoLottoMachineTest {
    private AutoLottoMachine autoLottoMachine = new AutoLottoMachine(new Money(1000));

    @DisplayName("금액에 맞추어 티켓을 생성하는지 확인")
    @Test
    void 금액에_맞추어_티켓을_생성하는지() {
        int expectedNumberOfTickets = 5;
        Money purchaseMoney = new Money(5000);

        int numberOfTickets = autoLottoMachine.calculateNumberOfTickets(purchaseMoney);
        LottoTickets tickets = autoLottoMachine.createTickets(numberOfTickets);

        assertThat(tickets.size()).isEqualTo(expectedNumberOfTickets);
    }

    @DisplayName("구매금액 단위가 맞지 않으면 예외처리하는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {5500, 4900, 1234, 2500})
    void 금액단위_안맞으면_예외(int purchaseMoneyAmount) {
        Money purchaseMoney = new Money(purchaseMoneyAmount);
        assertThatThrownBy(() -> autoLottoMachine.calculateNumberOfTickets(purchaseMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}