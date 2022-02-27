package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LottoResultsTest {
    @Test
    void 로또당첨_등수_개수() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticketFirst = getFirstTicket();
        Ticket ticketFirst2 = getFirstTicket();
        Ticket ticketSecond = getSecondTicket();
        Ticket ticketThird = getThirdTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticketFirst, ticketSecond, ticketFirst2, ticketThird));
        LottoResults result = LottoResults.of(winningNumbers, tickets);

        assertThat(result.getLottoResults()).hasSize(3)
                .contains(entry(Rank.FIRST, 2), entry(Rank.SECOND, 1), entry(Rank.THIRD, 1));
    }

    @Test
    void 수익률_0() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticket1 = getOtherTicket();
        Ticket ticket2 = getOtherTicket();
        Ticket ticket3 = getOtherTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3));
        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(3000).getYield(results.getProfit())).isEqualTo(0);
    }

    @Test
    void 수익률_1000() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticket1 = getOtherTicket();
        Ticket ticket2 = getThirdTicket();
        Ticket ticket3 = getThirdTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3));

        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(3000).getYield(results.getProfit())).isEqualTo(1000);
    }

    @Test
    void 수익률_035() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticket1 = getFifthTicket();
        Ticket ticket2 = getOtherTicket();
        Ticket ticket3 = getOtherTicket();
        Ticket ticket4 = getOtherTicket();
        Ticket ticket5 = getOtherTicket();
        Ticket ticket6 = getOtherTicket();
        Ticket ticket7 = getOtherTicket();
        Ticket ticket8 = getOtherTicket();
        Ticket ticket9 = getOtherTicket();
        Ticket ticket10 = getOtherTicket();
        Ticket ticket11 = getOtherTicket();
        Ticket ticket12 = getOtherTicket();
        Ticket ticket13 = getOtherTicket();
        Ticket ticket14 = getOtherTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3,
                ticket4, ticket5, ticket6, ticket7,
                ticket8, ticket9, ticket10, ticket11,
                ticket12, ticket13, ticket14));

        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(14000).getYield(results.getProfit())).isEqualTo(0.35);
    }

    @Test
    void 수익률_1등_3명() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticket1 = getFirstTicket();
        Ticket ticket2 = getFirstTicket();
        Ticket ticket3 = getFirstTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3));

        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(3000).getYield(results.getProfit())).isEqualTo(2000000);
        System.out.println();
    }

    @Test
    void 수익률_1등_14명() {
        WinningNumbers winningNumbers = getWinningNumbers();

        Ticket ticket1 = getFirstTicket();
        Ticket ticket2 = getFirstTicket();
        Ticket ticket3 = getFirstTicket();
        Ticket ticket4 = getFirstTicket();
        Ticket ticket5 = getFirstTicket();
        Ticket ticket6 = getFirstTicket();
        Ticket ticket7 = getFirstTicket();
        Ticket ticket8 = getFirstTicket();
        Ticket ticket9 = getFirstTicket();
        Ticket ticket10 = getFirstTicket();
        Ticket ticket11 = getFirstTicket();
        Ticket ticket12 = getFirstTicket();
        Ticket ticket13 = getFirstTicket();
        Ticket ticket14 = getFirstTicket();

        Tickets tickets = new Tickets(Arrays.asList(ticket1, ticket2, ticket3,
                ticket4, ticket5, ticket6, ticket7,
                ticket8, ticket9, ticket10, ticket11,
                ticket12, ticket13, ticket14));

        LottoResults results = LottoResults.of(winningNumbers, tickets);
        assertThat(new Amount(14000).getYield(results.getProfit())).isEqualTo(2000000);
        System.out.println();
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

    private Ticket getFifthTicket() {
        return new Ticket(Set.of(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(43)));
    }

    private Ticket getOtherTicket() {
        return new Ticket(Set.of(new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(43)));
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
