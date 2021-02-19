package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.winnerlotto.LottoWinnerTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketServiceTest {

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicket() {
        assertThat(LottoTicketService.createLottoTicket()).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("당첨 번호를 생성한다.")
    public void createLottoWinnerTicket() {
        LottoWinnerTicket lottoWinnerTicket = LottoTicketService.createLottoWinnerTicket("1,2,3,4,5,6");

        assertThat(lottoWinnerTicket).isInstanceOf(LottoWinnerTicket.class);
    }
}
