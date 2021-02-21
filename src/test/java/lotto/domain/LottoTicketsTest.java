package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    private final LottoNumberGenerator lottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
    private final ManualTicketNumbers manualTicketNumbers = ManualTicketNumbers.from("1, 2, 3, 4, 5, 6".split(", "));
    private final WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

    @DisplayName("당첨 번호와 구매한 로또 티켓을 비교하여 결과를 반환한다.")
    @Test
    void getLottoResult() {
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(3, lottoNumberGenerator);

        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        long secondPrizeTicketCounts = lottoResult.getStatistics().get(LottoRank.SECOND_PRIZE);

        assertThat(secondPrizeTicketCounts).isEqualTo(3);
    }

    @DisplayName("수동 로또 티켓을 구매한다")
    @Test
    void makeManualLottoTickets() {
        LottoTickets lottoTickets = LottoTickets.generateManual(Arrays.asList(manualTicketNumbers, manualTicketNumbers));
        long secondPrizeTicketCounts = lottoTickets.checkResult(winningLottoTicket)
                .getStatistics()
                .get(LottoRank.SECOND_PRIZE);

        assertThat(secondPrizeTicketCounts).isEqualTo(2);
    }

    @DisplayName("자동 생성 티켓들과 수동 생성 티켓들을 합쳐서 하나의 티켓으로 반환하는 기능 구현")
    @Test
    void concat() {
        LottoTickets manualLottoTickets = LottoTickets.generateManual(Arrays.asList(manualTicketNumbers, manualTicketNumbers));
        LottoTickets automaticLottoTickets = LottoTickets.generateAutomatic(3, lottoNumberGenerator);

        LottoTickets concatTickets = manualLottoTickets.concat(automaticLottoTickets);
        int totalTicketCounts = concatTickets.getTicketCounts();

        assertThat(totalTicketCounts).isEqualTo(5);
    }

    @DisplayName("구매한 로또 티켓의 개수를 반환한다")
    @Test
    void getTicketCounts() {
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(3, lottoNumberGenerator);

        int ticketCounts = lottoTickets.getTicketCounts();

        assertThat(ticketCounts).isEqualTo(3);
    }
}
