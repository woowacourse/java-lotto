package lotto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.analysis.Analysis;
import lotto.domain.money.Money;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketBundles;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.winning.WinningTicket;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketBundlesDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.view.LottoView;

public class LottoApplication {

    public final LottoView lottoView;

    public LottoApplication(final LottoView lottoView) {
        this.lottoView = lottoView;
    }

    public void run() {
        final Money money = insertMoney();
        final TicketBundles ticketBundles = purchaseTickets(money);
        announceTickets(ticketBundles);

        final Analysis analysis = calculateAnalysis(ticketBundles, money);
        announceAnalysis(analysis);
    }

    private Money insertMoney() {
        return new Money(lottoView.requestMoney());
    }

    private TicketBundles purchaseTickets(final Money money) {
        final int totalTicketCount = money.getQuotient();
        final int manualTicketCount = lottoView.requestManualTicketCount(totalTicketCount);
        final List<Ticket> manualTickets = requestManualTickets(manualTicketCount);
        return TicketBundles.generateTicketBundles(totalTicketCount, manualTickets, new RandomTicketGenerator());
    }

    private List<Ticket> requestManualTickets(final int manualTicketCount) {
        final List<TicketDto> manualTicketDtos = lottoView.requestManualTicketDtos(manualTicketCount);
        return manualTicketDtos.stream()
                .map(TicketDto::toTicket)
                .collect(Collectors.toUnmodifiableList());
    }

    private void announceTickets(final TicketBundles ticketBundles) {
        final TicketBundlesDto ticketBundlesDto = TicketBundlesDto.toDto(ticketBundles);
        lottoView.announceTickets(ticketBundlesDto);
    }

    private Analysis calculateAnalysis(final TicketBundles ticketBundles, final Money money) {
        final WinningTicket winningTicket = requestWinningTicket();
        final List<Rank> ranks = ticketBundles.calculateRanks(winningTicket);
        return new Analysis(ranks, money);
    }

    private WinningTicket requestWinningTicket() {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicketDto();
        return winningTicketDto.toWinningTicket();
    }

    private void announceAnalysis(final Analysis analysis) {
        final AnalysisDto analysisDto = AnalysisDto.toDto(analysis);
        lottoView.announceAnalysis(analysisDto);
    }

}
