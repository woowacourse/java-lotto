package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketMachineTest {
    @Test
    @DisplayName("구입 금액에 해당하는 만큼 로또 티켓 발급")
    void lottoMachineInsertMoney(){
        LottoTicketMachine lottoTicketMachine = new LottoTicketMachine();
        assertThat(lottoTicketMachine.buyTickets(new Money("999")).toList().size()).isEqualTo(0);
        assertThat(lottoTicketMachine.buyTickets(new Money("1000")).toList().size()).isEqualTo(1);
        assertThat(lottoTicketMachine.buyTickets(new Money("3000")).toList().size()).isNotEqualTo(2);
    }
}
