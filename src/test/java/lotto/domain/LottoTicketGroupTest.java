package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.TestLottoFactory;
import lotto.utils.RandomNumberGenerator;
import lotto.utils.StringNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketGroupTest {

    @Test
    @DisplayName("로또 구매가 정상적으로 이루어졌는지 확인")
    void createTotalLottoTickets() {
        // when
        LottoTickets autoTicket = LottoTickets.buyTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), 1);
        String input = "1,2,3,4,5,6";
        LottoTickets manualTicket = LottoTickets.buyTicket(new StringNumberGenerator(input), 1);
        LottoTicketGroup totalLottoTicket = new LottoTicketGroup(manualTicket, autoTicket);
        // then
        Assertions.assertThat(totalLottoTicket.getTotalTickets().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("로또 티켓 당첨자들의 통계가 정상적인지 확인")
    void findLottoWinners() {
        // given
        List<LottoNumber> numbers = TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6);
        List<LottoNumber> otherNumbers = TestLottoFactory.getNumbers(1, 2, 3, 10, 11, 12);
        LottoTickets manualTicket = new LottoTickets(List.of(new LottoTicket(numbers)));
        LottoTickets autoTicket = new LottoTickets(List.of(new LottoTicket(otherNumbers)));
        LottoTicketGroup totalTicket = new LottoTicketGroup(manualTicket, autoTicket);
        // when
        LottoStatistics lottoStatistics = totalTicket.findLottoWinners(
            new WinningTicket(new LottoTicket(numbers), new LottoNumber(7)));
        // then
        assertThat(lottoStatistics).isEqualTo(new LottoStatistics(
            List.of(LottoRank.FIRST, LottoRank.FIFTH)));
    }
}
