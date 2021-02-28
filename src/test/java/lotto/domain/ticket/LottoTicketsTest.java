package lotto.domain.ticket;


import lotto.domain.ticket.AutoTickets;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @DisplayName("로또 티켓 개수 테스트")
    @Test
    void Should_ReturnLottoTickets_When_AddedLottoTickets() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(LottoNumber.of(1));
        lottoNumbers.add(LottoNumber.of(2));
        lottoNumbers.add(LottoNumber.of(3));
        lottoNumbers.add(LottoNumber.of(4));
        lottoNumbers.add(LottoNumber.of(5));
        lottoNumbers.add(LottoNumber.of(6));

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbers);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers);

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(lottoTicket1, lottoTicket2));
        // TODO 확인
        LottoTickets purchasedLottoTickets = new AutoTickets(lottoTickets);

        assertThat(purchasedLottoTickets.size()).isEqualTo(2);
    }
}
