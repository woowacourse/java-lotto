package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TicketsTest {

    @Test
    void 티켓_생성() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(5);
    }

    @Test
    void 로또당첨_등수_개수() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticketFirst = getFirstTicket();
        Ticket ticketFirst2 = getFirstTicket();
        Ticket ticketSecond = getSecondTicket();
        Ticket ticketThird = getThirdTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticketFirst, ticketSecond, ticketFirst2, ticketThird));
        Map<Rank, Integer> result = tickets.getResult(winningNumbers);

        assertThat(result).hasSize(3)
            .contains(entry(Rank.FIRST, 2), entry(Rank.SECOND, 1), entry(Rank.THIRD, 1));
    }

    private WinningNumbers getWinningNumbers() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(6);
        return new WinningNumbers(winTicket, bonusNumber);
    }

    private Ticket getThirdTicket() {
        return new Ticket(Set.of(new LottoNumber(40),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16)));
    }

    private Ticket getSecondTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
    }

    private Ticket getFirstTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16)));
    }
}
