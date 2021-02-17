package lotto.domain;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketsTest {

    @Test
    void makeLottoTickets() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(3000);
        int lottoTicketCounts = purchasingPrice.calculateLottoTicketCounts();
        LottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(lottoTicketCounts, randomLottoNumberGenerator);
        assertThat(lottoTickets.size()).isEqualTo(3);
    }
}