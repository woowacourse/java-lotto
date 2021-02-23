package lottogame.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket 삽입 테스트")
    void lottoTicketsAddTest() {
        LottoTicket lottoTicket1 = new LottoAutoTicket();

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);

        assertThat(lottoTickets.toList().size()).isEqualTo(1);
    }
}
