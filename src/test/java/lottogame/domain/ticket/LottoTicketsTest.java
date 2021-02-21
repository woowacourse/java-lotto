package lottogame.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import lottogame.domain.Money;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket 삽입 테스트")
    void lottoTicketsAddTest() {
        LottoTickets lottoTickets = LottoTicketIssueMachine.issueTickets(new Money(3000));
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(3);

        Set<LottoNumber> lottoNumbers = new HashSet<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoTickets.add(new LottoTicket(lottoNumbers));
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(4);
    }
}
