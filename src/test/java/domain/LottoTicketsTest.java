package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 가격에 맞춰 로또 객체 생성 시 성공")
    @Test
    void purchaseBy_createsExactAmountOfLottoTickets_Successful() {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.purchaseAutoBy(5);

        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(5);
    }

    @DisplayName("중복되지 않는 로또 티켓 객체 생성 시 성공")
    @Test
    void purchaseBy_createsDistinctLottoTickets_Successful() {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.purchaseAutoBy(20);

        Set<LottoTicket> noDuplicateLottoTicketSet = new HashSet<>(lottoTickets.getLottoTickets());
        assertThat(noDuplicateLottoTicketSet.size())
                .isEqualTo(lottoTickets.getLottoTickets().size());
    }
}
