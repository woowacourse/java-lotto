package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private final NumberGenerateStrategy numberGenerateStrategy = () -> Set.of(1, 2, 3, 4, 5, 6);
    private final LottoMachine lottoMachine = new LottoMachine(numberGenerateStrategy);

    @Test
    @DisplayName("로또 게임이 입력받은 금액으로 구매를 잘 하는지 확인한다.")
    void checkMoneyTicketCount() {
        LottoMoney purchaseMoney = new LottoMoney(17000);
        LottoTickets lottoTickets = lottoMachine.purchaseTickets(purchaseMoney, new LottoCount(0),
                LottoTickets.fromTicketNumbers(new ArrayList<>()));

        assertThat(lottoTickets.getTickets().size()).isEqualTo(purchaseMoney.getValue() / LottoMachine.TICKET_PRICE);
    }
}