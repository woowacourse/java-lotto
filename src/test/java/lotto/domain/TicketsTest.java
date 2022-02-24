package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import lotto.utils.RandomLottoNumbersGenerator;

public class TicketsTest {

    @Test
    void 티켓_생성() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(5);
    }

    @Test
    void 로또당첨_등수_개수() {
        WinningNumbers winningNumbers = getWinningNumbers();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getFirstTicket());
        testTickets.add(getFirstTicket());
        testTickets.add(getSecondTicket());
        testTickets.add(getThirdTicket());

        Tickets tickets = new Tickets(testTickets);
        Map<Rank, Integer> result = tickets.getResult(winningNumbers);

        assertThat(result).hasSize(3)
            .contains(entry(Rank.FIRST, 2), entry(Rank.SECOND, 1), entry(Rank.THIRD, 1));
    }

    @Test
    void 수익률_0() {
        WinningNumbers winningNumbers = getWinningNumbers();

        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            testTickets.add(getOtherTicket());
        }

        Tickets tickets = new Tickets(testTickets);

        assertThat(tickets.getYield(new Amount(3000), winningNumbers)).isEqualTo(0);
    }

    @Test
    void 수익률_1000() {
        WinningNumbers winningNumbers = getWinningNumbers();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getOtherTicket());
        testTickets.add(getThirdTicket());
        testTickets.add(getThirdTicket());

        Tickets tickets = new Tickets(testTickets);

        assertThat(tickets.getYield(new Amount(3000), winningNumbers)).isEqualTo(1000);
    }

    @Test
    void 수익률_035() {
        WinningNumbers winningNumbers = getWinningNumbers();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getFifthTicket());
        for (int i = 0; i < 13; i++) {
            testTickets.add(getOtherTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        assertThat(tickets.getYield(new Amount(14000), winningNumbers)).isEqualTo(0.35);
    }

    @Test
    void 수익률_1등_3명() {
        WinningNumbers winningNumbers = getWinningNumbers();

        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            testTickets.add(getFirstTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        assertThat(tickets.getYield(new Amount(3000), winningNumbers)).isEqualTo(2000000);
    }

    @Test
    void 수익률_1등_14명() {
        WinningNumbers winningNumbers = getWinningNumbers();
        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            testTickets.add(getFirstTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        assertThat(tickets.getYield(new Amount(14000), winningNumbers)).isEqualTo(2000000);
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
