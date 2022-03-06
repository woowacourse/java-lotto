package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.utils.RandomLottoNumbersGenerator;

public class TicketsTest {

    @Test
    @DisplayName("생성된 tickets의 개수가 5개여야 합니다.")
    void ticketsCreateValidTest() {
        Tickets tickets = Tickets.of(5, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets()).hasSize(5);
    }

    @Test
    @DisplayName("로또 당첨 등수와 당첨 개수가 1등 2개, 2등 1개, 3등 1개가 나와야 합니다.")
    void ticketsResultTest() {
        WinTicket winTicket = getWinTicket();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getFirstTicket());
        testTickets.add(getFirstTicket());
        testTickets.add(getSecondTicket());
        testTickets.add(getThirdTicket());

        Tickets tickets = new Tickets(testTickets);
        Map<Rank, Integer> result = tickets.getResult(winTicket);

        assertThat(result).hasSize(3)
            .contains(entry(Rank.FIRST, 2), entry(Rank.SECOND, 1), entry(Rank.THIRD, 1));
    }

    @Test
    @DisplayName("하나라도 당첨되지 않으면 수익율은 0이어야 합니다.")
    void yieldTest0() {
        WinTicket winTicket = getWinTicket();

        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            testTickets.add(getOtherTicket());
        }

        Tickets tickets = new Tickets(testTickets);

        double yield = tickets.getYield(new Amount(3000), winTicket);
        assertThat(yield).isEqualTo(0);
    }

    @Test
    @DisplayName("3,000원 투입에 1,500,000원 당첨(3등)이 2개이면 수익율은 1000이어야 합니다.")
    void yieldTest1() {
        WinTicket winTicket = getWinTicket();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getOtherTicket());
        testTickets.add(getThirdTicket());
        testTickets.add(getThirdTicket());

        Tickets tickets = new Tickets(testTickets);

        double yield = tickets.getYield(new Amount(3000), winTicket);
        assertThat(yield).isEqualTo(1000);
    }

    @Test
    @DisplayName("14,000원 투입에 5,000원(5등) 당첨이면 수익율은 0.35이어야 합니다.")
    void yieldTest035() {
        WinTicket winTicket = getWinTicket();

        List<Ticket> testTickets = new ArrayList<>();
        testTickets.add(getFifthTicket());
        for (int i = 0; i < 13; i++) {
            testTickets.add(getOtherTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        double yield = tickets.getYield(new Amount(14000), winTicket);
        assertThat(yield).isEqualTo(0.35);
    }

    @Test
    @DisplayName("8000원 투입에 1등 당첨이 4명일때 수익율은 1,000,000원이어야 합니다.")
    void yieldTest1000000() {
        WinTicket winTicket = getWinTicket();

        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            testTickets.add(getFirstTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        double yield = tickets.getYield(new Amount(8000), winTicket);
        assertThat(yield).isEqualTo(1_000_000);
    }

    @Test
    @DisplayName("14,000원 투입에 1등 당첨이 14명일때 수익율은 2,000,000원이어야 합니다.")
    void yieldTest2000000() {
        WinTicket winTicket = getWinTicket();
        List<Ticket> testTickets = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            testTickets.add(getFirstTicket());
        }
        Tickets tickets = new Tickets(testTickets);

        double yield = tickets.getYield(new Amount(14000), winTicket);
        assertThat(yield).isEqualTo(2000000);
    }


    private WinTicket getWinTicket() {
        Ticket winTicket = Ticket.createByIntegers(Arrays.asList(1, 2, 3, 4, 5, 16), false);
        LottoNumber bonusNumber = new LottoNumber(6);
        return new WinTicket(winTicket, bonusNumber);
    }

    private Ticket getFifthTicket() {
        return Ticket.createByIntegers(Arrays.asList(1, 2, 3, 14, 15, 43), false);
    }

    private Ticket getOtherTicket() {
        return Ticket.createByIntegers(Arrays.asList(11, 12, 13, 14, 15, 43), false);
    }

    private Ticket getThirdTicket() {
        return Ticket.createByIntegers(Arrays.asList(40, 2, 3, 4, 5, 16), false);
    }

    private Ticket getSecondTicket() {
        return Ticket.createByIntegers(Arrays.asList(1, 2, 3, 4, 5, 6), false);
    }

    private Ticket getFirstTicket() {
        return Ticket.createByIntegers(Arrays.asList(1, 2, 3, 4, 5, 16), false);
    }
}
