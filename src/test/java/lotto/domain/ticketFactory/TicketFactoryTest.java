package lotto.domain.ticketFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TicketFactoryTest {

    TicketFactory ticketFactory = new TicketFactory();

    @DisplayName("입력한 갯수만큼 로또 티켓이 만들어진다.")
    @Test
    void createTickets() {
        LottoTickets lottoTickets = ticketFactory.makeRandomTicketsByCount(3);

        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(3);
    }

    @DisplayName("입력한 번호들을 가진 로또 티켓이 만들어진다.")
    @Test
    void createTicket() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Set<Integer> actual = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket lottoTicket = ticketFactory.makeFixedTicket(numbers);

        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(actual);
    }

}