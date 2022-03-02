package lotterymachine.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("당첨 금액을 투입된 금액으로 나눈다.")
    void divide() {
        Money inputMoney = Money.fromInputAmount(14000);
        Money winningMoney = Money.from(5000);
        double expected = 5000.0 / 14000;
        assertThat(winningMoney.divide(inputMoney)).isEqualTo(expected);
    }

    @Test
    @DisplayName("투입된 금액을 통해 구매할 수 있는 로또 티켓 개수를 구한다.")
    void divideByTicketPrice() {
        Money inputMoney = Money.fromInputAmount(14000);
        int numberOfTickets = inputMoney.divideByTicketPrice();
        assertThat(numberOfTickets).isEqualTo(14);
    }
}
