package lotto.domain;


import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
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

        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        purchasedLottoTickets.add(lottoTicket1);
        purchasedLottoTickets.add(lottoTicket2);

        assertThat(purchasedLottoTickets.getTickets().size()).isEqualTo(2);
    }
}
