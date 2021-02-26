package lotto.service;

import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketServiceTest {

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicket() {
        assertThat(LottoTicketService.createAutoLottoTicket()).isInstanceOf(LottoTicket.class);
    }
}
