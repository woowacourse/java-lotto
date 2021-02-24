package lottogame.domain.machine;

import lottogame.domain.LottoManualTicketCount;
import lottogame.domain.Money;
import lottogame.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketMachineTest {

    LottoTicketMachine lottoTicketMachine = new LottoTicketMachine();

    @Test
    @DisplayName("구입 금액에 해당하는 만큼 로또 티켓 발급")
    void lottoTicketMachineInsertMoney() {
        assertThat(lottoTicketMachine.buyTickets(new Money("999")).toList().size()).isEqualTo(0);
        assertThat(lottoTicketMachine.buyTickets(new Money("1000")).toList().size()).isEqualTo(1);
        assertThat(lottoTicketMachine.buyTickets(new Money("3000")).toList().size())
                .isNotEqualTo(2);
    }

    @Test
    @DisplayName("수동 2장 자동 3장을 구매해본다")
    void manualAndAutoBuy() {
        Money money = new Money("5000");
        LottoManualTicketCount ticketCount = new LottoManualTicketCount("2", money);

        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < ticketCount.value(); ++i) {
            lottoTickets.add(lottoTicketMachine.buyManualTicket(money, "1,2,3,4,5,6"));
        }
        assertThat(lottoTickets.toList().size()).isEqualTo(2);
        assertThat(lottoTicketMachine.buyAutoTickets(money).toList().size()).isEqualTo(3);
    }
}
