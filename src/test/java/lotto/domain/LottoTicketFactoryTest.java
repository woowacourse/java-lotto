package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.vo.ManualTicketCount;
import lotto.domain.vo.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTicketFactoryTest {

    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("수동으로 구매한 로또가 없을 경우 자동 로또만 구매한다.")
    void purchaseLotto2(String money, int totalTicketCount) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);
        ManualTicketCount manualTicketCount = new ManualTicketCount("0", purchaseAmount);
        LottoTickets manualLottoTickets = new LottoTickets(new ArrayList<>());

        // when
        TicketPurchaseDecider ticketPurchaseDecider = new TicketPurchaseDecider(purchaseAmount, manualTicketCount);
        List<LottoTicket> createdLottoTickets = lottoTicketFactory.createTickets(ticketPurchaseDecider, manualLottoTickets);

        // then
        assertThat(ticketPurchaseDecider.getAutoTicketCount()).isEqualTo(totalTicketCount - manualLottoTickets.size());
        assertThat(createdLottoTickets.size()).isEqualTo(totalTicketCount);
    }

    @ParameterizedTest
    @CsvSource(value = {"7500,7", "8000,8"})
    @DisplayName("수동으로 구매한 로또가 있을 경우 자동 로또의 개수는 수동 구매 장수를 차감하여 구해진다.")
    void purchaseLotto(String money, int totalTicketCount) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(money);
        ManualTicketCount manualTicketCount = new ManualTicketCount("2", purchaseAmount);
        LottoTickets manualLottoTickets = new LottoTickets(List.of("1,2,3,4,5,6", "7,8,9,10,11,12"));

        // when
        TicketPurchaseDecider ticketPurchaseDecider = new TicketPurchaseDecider(purchaseAmount, manualTicketCount);
        List<LottoTicket> createdLottoTickets = lottoTicketFactory.createTickets(ticketPurchaseDecider, manualLottoTickets);

        // then
        assertThat(ticketPurchaseDecider.getAutoTicketCount()).isEqualTo(totalTicketCount - manualLottoTickets.size());
        assertThat(createdLottoTickets.size()).isEqualTo(totalTicketCount);
    }
}
