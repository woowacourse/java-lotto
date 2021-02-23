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
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(LottoNumbers.of(1));
        lottoNumbers.add(LottoNumbers.of(2));
        lottoNumbers.add(LottoNumbers.of(3));
        lottoNumbers.add(LottoNumbers.of(4));
        lottoNumbers.add(LottoNumbers.of(5));
        lottoNumbers.add(LottoNumbers.of(6));

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbers);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers);

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);

        assertThat(lottoTickets.size()).isEqualTo(2);
        assertThat(lottoTickets.getAll().size()).isEqualTo(2);
    }
}
