package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    @Test
    void of() {
        Money purchaseMoney = Money.ofPurchaseMoney(14000);
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, 3);
        List<String> manualLottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");
        assertThat(LottoTickets.of(ticketCounts, manualLottoNumbers)).isNotNull();
    }

    @Test
    void size() {
        Money purchaseMoney = Money.ofPurchaseMoney(14000);
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, 3);
        List<String> manualLottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");
        LottoTickets lottoTickets = LottoTickets.of(ticketCounts, manualLottoNumbers);

        assertThat(lottoTickets.size()).isEqualTo(purchaseMoney.calculateAllTicketCount());
    }

    @Test
    void checkOutLottos() {
        Money purchaseMoney = Money.ofPurchaseMoney(14000);
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, 3);
        List<String> manualLottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,4,6,8,10,12", "3, 5, 15, 20, 30, 45");
        LottoTickets lottoTickets = LottoTickets.of(ticketCounts, manualLottoNumbers);

        LottoTicket winningLottoTicket = LottoTicket.fromInput("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(7);

        Ranks ranks = lottoTickets.checkOutLottos(winningLottoTicket, bonusNumber);

        assertThat(ranks.contains(Rank.FIRST)).isTrue();
        assertThat(ranks.contains(Rank.FIFTH)).isTrue();
    }
}