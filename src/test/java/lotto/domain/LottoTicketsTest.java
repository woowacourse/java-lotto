package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 티켓 추가 확인")
    @Test
    public void lottoTicketsAdd() {
        Lotto lottoTicket = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(lottoTicket);

        assertThat(lottoTickets.getLottoTickets()).isNotEmpty();
    }

    @DisplayName("로또 티켓수 계산 확인")
    @Test
    public void lottoTicketsCount() {
        Lotto lottoTicket1 = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        Lotto lottoTicket2 = new Lotto(Set.of(1, 2, 3, 4, 5, 7));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(lottoTicket1);
        lottoTickets.addLottoTicket(lottoTicket2);

        assertThat(lottoTickets.getLottoTicketsCount()).isEqualTo(2);
    }
}
