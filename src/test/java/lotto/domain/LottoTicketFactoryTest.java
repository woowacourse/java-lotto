package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lottonumber.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTicketFactoryTest {

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("천원 당 한 장의 로또를 구매할 수 있다.")
    void purchaseLotto(String money, int ticketCount) {
        // given

        // when
        List<LottoTicket> lottoTickets = LottoTicketFactory.createTickets(new PurchaseAmount(money));

        // then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }
}
