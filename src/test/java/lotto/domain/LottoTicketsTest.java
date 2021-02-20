package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketsTest {

    private final LottoNumberGenerator lottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);

    @DisplayName("구매 금액이 부족하여 로또 구매가 불가능한 경우 예외 발생")
    @Test
    void cannotMakeLottoTicket() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(930);

        assertThatCode(() -> {
            LottoTickets.generateAutomatic(purchasingPrice, lottoNumberGenerator);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액이 부족하여 로또 티켓을 구매할 수 없습니다.");
    }

    @DisplayName("당첨 번호와 구매한 로또 티켓을 비교하여 결과를 반환한다.")
    @Test
    void getLottoResult() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(3000);
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(purchasingPrice, lottoNumberGenerator);
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        int secondPrizeTicketCounts = (int) lottoResult.getTicketCountsByRank(LottoRank.SECOND_PRIZE);

        assertThat(secondPrizeTicketCounts).isEqualTo(3);
    }

    @DisplayName("구매한 로또 티켓의 개수를 반환한다")
    @Test
    void getTicketCounts() {
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(new PurchasingPrice(3127), lottoNumberGenerator);

        int ticketCounts = lottoTickets.getTicketCounts();

        assertThat(ticketCounts).isEqualTo(3);
    }

    @DisplayName("로또 티켓을 구매하는데 든 금액을 반환한다")
    @Test
    void getPurchasingPrice() {
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(new PurchasingPrice(4000), lottoNumberGenerator);

        int purchasingPrice = lottoTickets.getPurchasingPrice();

        assertThat(purchasingPrice).isEqualTo(4000);
    }
}
