package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.vo.ManualTicketSize;
import lotto.domain.vo.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTicketFactoryTest {

    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("수동으로 구매한 로또가 있을 경우 천원 당 한 장의 로또를 구매할 수 있다. ")
    void purchaseLotto(String money, int ticketCount) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);
        List<String> manualTickets = List.of("1,2,3,4,5,6", "7,8,9,10,11,12");

        // when
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount, manualTickets);

        // then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("수동으로 구매한 로또가 없을 경우 자동 로또만 구매한다.")
    void purchaseLotto2(String money, int ticketCount) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);
        List<String> manualTickets = new ArrayList<>();

        // when
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount, manualTickets);

        // then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }
}
