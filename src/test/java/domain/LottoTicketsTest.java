package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    void of_createTheSameNumberOfLottosAsLottoCount() {
        LottoTickets lottoTickets = LottoTickets.purchaseBy(5000);

        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(5);
    }

    @Test
    void of_eachLottoIsDifferent() {
        LottoTickets lottoTickets = LottoTickets.purchaseBy(20000);

        Set<LottoTicket> noDuplicateLottoTicketSet = new HashSet<>(lottoTickets.getLottoTickets());
        assertThat(noDuplicateLottoTicketSet.size())
                .isEqualTo(lottoTickets.getLottoTickets().size());
    }
}
