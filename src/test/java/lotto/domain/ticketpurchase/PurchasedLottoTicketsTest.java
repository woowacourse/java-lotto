package lotto.domain.ticketpurchase;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchasedLottoTicketsTest {
    @DisplayName("로또 티켓 추가 및 꺼내기 테스트")
    @Test
    void Should_ReturnLottoTickets_When_AddedLottoTickets() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
        lottoNumbers.add(new LottoNumber(6));

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbers);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers);

        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(lottoTicket1, lottoTicket2));
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets(lottoTickets);

        assertThat(purchasedLottoTickets.getTickets().size()).isEqualTo(2);
    }
}
