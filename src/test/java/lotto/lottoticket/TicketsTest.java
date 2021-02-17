package lotto.lottoticket;

import lotto.lottogame.LottoCount;
import lotto.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TicketsTest {
    @Test
    @DisplayName("티켓들 생성 확인")
    void ticketsCreate() {
        Tickets tickets = new Tickets(new LottoCount(new Money("20000")), new RandomNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(20);
    }
}
