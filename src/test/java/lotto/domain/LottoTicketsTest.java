package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @DisplayName("금액에 맞게 로또 티켓을 구입했는지 확인한다.")
    @Test
    void makeLottoTickets() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(3000);
        int lottoTicketCounts = purchasingPrice.calculatePurchasableTicketCounts();
        LottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();

        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(lottoTicketCounts, randomLottoNumberGenerator);

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("당첨 번호와 구매한 로또 티켓을 비교하여 결과를 반환한다.")
    @Test
    void getLottoResult() {
        LottoNumberGenerator lottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(3, lottoNumberGenerator);
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

        LottoStatistics lottoStatistics = lottoTickets.getStatistics(winningLottoTicket);

        assertThat(lottoStatistics.getCounts(LottoRank.SECOND)).isEqualTo(3);
    }
}