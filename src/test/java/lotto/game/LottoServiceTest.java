package lotto.game;

import lotto.ticket.Tickets;
import lotto.ticket.strategy.RandomNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoServiceTest {
    LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService(new RandomNumbersGenerator());
    }

    @Test
    @DisplayName("수동 로또 구매")
    void buyManualTicket() {
        List<String> ticketNumbers = Arrays.asList(
                "1,2,3,4,5,6",
                "1,3,5,7,8,10",
                "4,8,10,15,17,45"
        );
        Tickets tickets = lottoService.buyManualTickets(ticketNumbers);
        assertThat(tickets.getTickets().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동 로또 구매")
    void buyAutoTicket() {
        LottoCount lottoCount = new LottoCount("3");
        Tickets tickets = lottoService.buyAutoTickets(lottoCount);
        assertThat(tickets.getTickets().size()).isEqualTo(3);
    }
}
