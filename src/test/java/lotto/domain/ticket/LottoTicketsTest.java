package lotto.domain.ticket;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTicketsTest {
    @DisplayName("요소 개수가 제대로 반환되는지")
    @Test
    void size메소드_동작_확인() {
        LottoTicket ticket1 = LottoTicket.createLottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket ticket2 = LottoTicket.createLottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket ticket3 = LottoTicket.createLottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LottoTicket> ticketBundle = Arrays.asList(ticket1, ticket2, ticket3);

        LottoTickets tickets = new LottoTickets(ticketBundle);

        Assertions.assertThat(tickets.size()).isEqualTo(ticketBundle.size());
    }

    @DisplayName("우승 로또와 일반 로또가 일치했을 때, 알맞은 상금을 반환하는지 확인")
    @ParameterizedTest
    @CsvSource({"1,2,3,4,5,6,2000000000", "2,3,4,5,6,7,30000000", "2,3,4,5,6,8,1500000", "3,4,5,6,7,8,50000", "4,5,6,7,8,9,5000"})
    void calculateLottoResult(int first, int second, int third, int fourth, int fifth, int sixth, int expectedPrizeMoney) {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningNumbers, bonusNumber);

        LottoTicket expectedFirstPrize = LottoTicket.createLottoTicket(Arrays.asList(first, second, third, fourth, fifth, sixth));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(expectedFirstPrize));
        LottoResult lottoResult = lottoTickets.calculateLottoResult(winningLottoTicket, new Money(1000));

        assertThat(lottoResult.calculatePrizeMoney()).isEqualTo(expectedPrizeMoney);
    }
}