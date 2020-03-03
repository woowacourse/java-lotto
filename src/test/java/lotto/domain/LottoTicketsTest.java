package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @Test
    void ofAutoLottoTickets() {
        int autoTicketCount = 3;
        assertThat(LottoTickets.ofAutoLottoTickets(autoTicketCount)).isNotNull();
    }

    @Test
    void ofManualLottoTickets() {
        int manualTicketCount = 3;
        List<String> inputsForNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");

        assertThat(LottoTickets.ofManualLottoTickets(manualTicketCount, inputsForNumbers)).isNotNull();
    }

    @Test
    void join() {
        int autoTicketCount = 3;
        LottoTickets autoLottoTickets = LottoTickets.ofAutoLottoTickets(autoTicketCount);

        int manualTicketCount = 3;
        List<String> inputsForNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");
        LottoTickets manualLottoTickets = LottoTickets.ofManualLottoTickets(manualTicketCount, inputsForNumbers);

        int actual = LottoTickets.join(autoLottoTickets, manualLottoTickets).size();
        int expected = autoLottoTickets.size() + manualLottoTickets.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void size() {
        int autoTicketCount = 3;
        LottoTickets autoLottoTickets = LottoTickets.ofAutoLottoTickets(autoTicketCount);

        assertThat(autoLottoTickets.size()).isEqualTo(autoTicketCount);
    }

    @Test
    void checkOutLottos() {
        int manualTicketCount = 3;
        List<String> inputsForNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");
        LottoTickets manualLottoTickets = LottoTickets.ofManualLottoTickets(manualTicketCount, inputsForNumbers);

        LottoTicket winningLottoTicket = LottoTicket.fromInput("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(7);

        Ranks ranks = manualLottoTickets.checkOutLottos(winningLottoTicket, bonusNumber);

        assertThat(ranks.contains(Rank.FIRST)).isTrue();
        assertThat(ranks.contains(Rank.FIFTH)).isTrue();
    }
}