package lotto.controller;

import java.util.List;

import lotto.domain.analysis.Analysis;
import lotto.domain.money.Money;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;
import lotto.dto.WinningTicketDto;
import lotto.view.LottoView;

public class LottoController {

    public final LottoView lottoView;

    public LottoController(final LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        final Tickets tickets = purchaseTickets();
        announceTickets(tickets);

        final Analysis analysis = calculateAnalysis(tickets);
        announceAnalysis(analysis);
    }

    private Tickets purchaseTickets() {
        final int money = lottoView.requestMoney();
        final int ticketCount = (new Money(money)).getQuotient();
        return new Tickets(ticketCount, new RandomTicketGenerator());
    }

    private void announceTickets(final Tickets tickets) {
        lottoView.announceTickets(tickets);
    }

    private Analysis calculateAnalysis(final Tickets tickets) {
        final WinningTicket winningTicket = this.requestWinningTicket();
        final List<Rank> ranks = tickets.calculateRanks(winningTicket);
        return new Analysis(ranks, tickets.getSize());
    }

    private WinningTicket requestWinningTicket() {
        final List<Integer> winningNumbers = lottoView.requestWinningNumbers();
        final int bonusNumber = lottoView.requestBonusNumber();
        return new WinningTicket(winningNumbers, bonusNumber);
    }

    private  void announceAnalysis(final Analysis analysis) {
        lottoView.announceAnalysis(analysis);
    }

}
