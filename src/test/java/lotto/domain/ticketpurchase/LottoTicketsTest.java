package lotto.domain.ticketpurchase;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {
    @DisplayName("로또 티켓 추가 및 꺼내기 테스트")
    @Test
    void Should_Return_ExactLottoTickets_When_AfterAddLottoTickets() {
        // given
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number = 1; number <= 6; number++) {
            lottoNumbers.add(LottoNumbers.get(number));
        }

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbers);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers);

        // when
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);

        lottoTickets.addAll(lottoTickets);

        // then
        assertThat(lottoTickets.size()).isEqualTo(4);
        assertThat(lottoTickets.getAll().size()).isEqualTo(4);
    }
}
